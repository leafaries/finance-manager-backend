package com.leafaries.financemanagerbackend.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for User Registration information.
 * This class is used to transfer user registration data between different layers of the application.
 */
@Getter
@Setter
public class UserRegistrationDto {
    /**
     * The username of the user.
     * Must not be blank and must be between 3 and 50 characters.
     */
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    /**
     * The password of the user.
     * Must not be blank and must be between 8 and 50 characters.
     */
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    private String password;
}
