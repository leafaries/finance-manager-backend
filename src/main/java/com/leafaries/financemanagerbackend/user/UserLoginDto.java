package com.leafaries.financemanagerbackend.user;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for User Login information.
 * This class is used to transfer user login data between different layers of the application.
 */
@Getter
@Setter
public class UserLoginDto {
    /**
     * The username of the user attempting to log in.
     */
    private String username;

    /**
     * The password of the user attempting to log in.
     */
    private String password;
}
