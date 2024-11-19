package com.leafaries.financemanagerbackend.user;

/**
 * Data Transfer Object (DTO) for User Login information.
 * This class is used to transfer user login data between different layers of the application.
 */
public class UserLoginDto {

    /**
     * The username of the user attempting to log in.
     */
    private String username;

    /**
     * The password of the user attempting to log in.
     */
    private String password;

    // Getters and setters

    /**
     * Gets the username of the user attempting to log in.
     *
     * @return the username of the user attempting to log in.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user attempting to log in.
     *
     * @param username the username of the user attempting to log in.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user attempting to log in.
     *
     * @return the password of the user attempting to log in.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user attempting to log in.
     *
     * @param password the password of the user attempting to log in.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
