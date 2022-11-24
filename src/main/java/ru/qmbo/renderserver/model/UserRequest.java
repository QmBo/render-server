package ru.qmbo.renderserver.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * UserRequest
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 24.11.2022
 */
@Accessors(chain = true)
@Data
public class UserRequest {
    private String login;
    private String password;
}
