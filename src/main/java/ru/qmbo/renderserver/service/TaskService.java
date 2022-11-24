package ru.qmbo.renderserver.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.qmbo.renderserver.model.CreateRequest;
import ru.qmbo.renderserver.model.Stats;
import ru.qmbo.renderserver.model.Task;
import ru.qmbo.renderserver.model.User;
import ru.qmbo.renderserver.repository.TaskRepository;

import java.util.*;
import java.util.concurrent.Executor;

/**
 * TaskService
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 24.11.2022
 */
@Log4j2
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final static String RENDERING = "RENDERING";
    private final static String COMPLETE = "COMPLETE";

    /**
     * Instantiates a new Task service.
     *
     * @param taskRepository the task repository
     * @param userService    the user service
     */
    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }


    /**
     * Gets list tasks.
     *
     * @param token the token
     * @return the list tasks
     */
    public List<Task> getListTasks(String token) {
        if (token == null || token.length() < 30) {
            return Collections.emptyList();
        }
        return this.taskRepository.findAllByUserToken(token);
    }

    /**
     * Create task.
     *
     * @param request the request
     * @return the task
     */
    public Task createTask(CreateRequest request) {
        Task task = new Task();
        User user = this.userService.findUserByToken(request.getToken());
        if (user.getId() > 0) {
            task = new Task()
                    .setUser(user)
                    .setStartDate(new Date(System.currentTimeMillis()))
                    .setStatus(RENDERING);
            this.taskRepository.save(task);
            this.taskWork(task);
        }
        return task;
    }

    private void taskWork(Task task) {
        Runnable work = () -> {
            try {
                Thread.sleep((long) (Math.random() * 240 + 60) * 1000);
                task.setCompleteDate(new Date(System.currentTimeMillis()));
                task.setStatus(COMPLETE);
                this.taskRepository.save(task);
            } catch (InterruptedException e) {
                log.error("Interrupt exception.", e);
            }
        };
        Executor executor = (runnable) -> new Thread(runnable).start();
        executor.execute(work);
    }

    /**
     * Gets stats.
     *
     * @param token the token
     * @return the stats
     */
    public List<Stats> getStats(String token) {
        if (token == null || token.length() < 30) {
            return Collections.emptyList();
        }
        List<Task> tasks = this.taskRepository.findAllByUserToken(token);
        List<Stats> result = new ArrayList<>(100);
        tasks.forEach(
                task -> {
                    result.add(
                        new Stats()
                                .setId(task.getId())
                                .setDate(task.getStartDate())
                                .setStatus(RENDERING)
                    );
                    if (task.getCompleteDate() != null) {
                        result.add(
                                new Stats()
                                        .setId(task.getId())
                                        .setDate(task.getCompleteDate())
                                        .setStatus(task.getStatus())
                        );
                    }
                }
        );
        Collections.sort(result);
        return result;
    }
}
