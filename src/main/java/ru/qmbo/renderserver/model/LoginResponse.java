package ru.qmbo.renderserver.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * LoginResponse
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 24.11.2022
 */
@Accessors(chain = true)
@Data
public class LoginResponse {
    private String status;
    private String token;
}
