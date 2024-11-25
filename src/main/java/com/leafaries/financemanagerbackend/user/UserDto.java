package com.leafaries.financemanagerbackend.user;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for User information.
 * This class is used to transfer user data between different layers of the applications.
 */
@Getter
@Setter
public class UserDto {
    /**
     * The ID of the user.
     */
    private Long id;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The password of the user.
     */
    private String password;
}
