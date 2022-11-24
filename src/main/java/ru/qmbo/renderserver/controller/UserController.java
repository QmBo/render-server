package ru.qmbo.renderserver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.qmbo.renderserver.model.LoginResponse;
import ru.qmbo.renderserver.model.UserRequest;
import ru.qmbo.renderserver.model.RegUserResponse;
import ru.qmbo.renderserver.service.UserService;

/**
 * UserController
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 24.11.2022
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    /**
     * Instantiates a new User controller.
     *
     * @param service the service
     */
    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * Reg user reg user response.
     *
     * @param request the request
     * @return the reg user response
     */
    @PostMapping("/reg")
    public RegUserResponse regUser(@RequestBody UserRequest request) {
        return this.service.regUser(request);
    }

    /**
     * Login user login response.
     *
     * @param request the request
     * @return the login response
     */
    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody UserRequest request) {
        return this.service.loginUser(request);
    }
}
