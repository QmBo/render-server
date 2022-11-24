package ru.qmbo.renderserver.service;

import org.springframework.stereotype.Service;
import ru.qmbo.renderserver.model.LoginResponse;
import ru.qmbo.renderserver.model.RegUserResponse;
import ru.qmbo.renderserver.model.User;
import ru.qmbo.renderserver.model.UserRequest;
import ru.qmbo.renderserver.repository.UserRepository;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * UserService
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 24.11.2022
 */
@Service
public class UserService {
    private final UserRepository repository;
    private final static String ERROR = "error";
    private final static String OK = "ok";

    /**
     * Instantiates a new User service.
     *
     * @param repository the repository
     */
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Reg user.
     *
     * @param request the request
     * @return the reg user response
     */
    public RegUserResponse regUser(UserRequest request) {
        String reg = ERROR;
        if (request.getLogin().length() > 0 && request.getPassword().length() > 0) {
            if (this.repository.findUserByLogin(request.getLogin()).isEmpty()) {
                boolean write = this.writeUser(request);
                if (write) {
                    reg = OK;
                }
            }
        }
        return new RegUserResponse().setReg(reg);
    }

    private boolean writeUser(UserRequest request) {
        return this.repository.save(new User().setLogin(request.getLogin()).setPassword(request.getPassword()))
                .getId() != 0;
    }

    /**
     * Login user.
     *
     * @param request the request
     * @return the login response
     */
    public LoginResponse loginUser(UserRequest request) {
        AtomicReference<String> status = new AtomicReference<>(ERROR);
        AtomicReference<String> token = new AtomicReference<>("");
        this.repository.findUserByLoginAndPassword(request.getLogin(), request.getPassword())
                .ifPresent(user -> {
                    status.set(OK);
                    token.set(UUID.randomUUID().toString());
                    user.setToken(token.get());
                    this.repository.save(user);
                });
        return new LoginResponse().setStatus(status.get()).setToken(token.get());
    }

    /**
     * Find user by token.
     *
     * @param token the token
     * @return the user
     */
    public User findUserByToken(String token) {
        return this.repository.findByToken(token).orElse(new User().setId(0));
    }
}
