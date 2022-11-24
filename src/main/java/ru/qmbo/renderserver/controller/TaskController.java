package ru.qmbo.renderserver.controller;

import org.springframework.web.bind.annotation.*;
import ru.qmbo.renderserver.model.CreateRequest;
import ru.qmbo.renderserver.model.Stats;
import ru.qmbo.renderserver.model.Task;
import ru.qmbo.renderserver.service.TaskService;

import java.util.List;

/**
 * TaskController
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 24.11.2022
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    /**
     * Instantiates a new Task controller.
     *
     * @param taskService the task service
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Gets list.
     *
     * @param token the token
     * @return the list
     */
    @GetMapping("/list")
    public List<Task> getList(@RequestParam String token) {
        return this.taskService.getListTasks(token);
    }

    /**
     * Gets stats.
     *
     * @param token the token
     * @return the stats
     */
    @GetMapping("/stats")
    public List<Stats> getStats(@RequestParam String token) {
        return this.taskService.getStats(token);
    }

    /**
     * Create task task.
     *
     * @param request the request
     * @return the task
     */
    @PostMapping("/create")
    public Task createTask(@RequestBody CreateRequest request) {
        return this.taskService.createTask(request);
    }
}
