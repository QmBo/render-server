package ru.qmbo.renderserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.qmbo.renderserver.model.User;

import java.util.Optional;

/**
 * UserRepository
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 24.11.2022
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    /**
     * Find user by login optional.
     *
     * @param login the login
     * @return the optional
     */
    Optional<User> findUserByLogin(String login);

    /**
     * Find user by login and password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     */
    Optional<User> findUserByLoginAndPassword(String login, String password);

    /**
     * Find by token optional.
     *
     * @param token the token
     * @return the optional
     */
    Optional<User> findByToken(String token);
}
