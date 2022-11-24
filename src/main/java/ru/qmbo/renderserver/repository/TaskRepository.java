package ru.qmbo.renderserver.repository;

import org.springframework.data.repository.CrudRepository;
import ru.qmbo.renderserver.model.Task;

import java.util.List;

/**
 * TaskRepository
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 24.11.2022
 */
public interface TaskRepository extends CrudRepository<Task, Integer> {
    /**
     * Find all by user token.
     *
     * @param token the token
     * @return the list
     */
    List<Task> findAllByUserToken(String token);
}
