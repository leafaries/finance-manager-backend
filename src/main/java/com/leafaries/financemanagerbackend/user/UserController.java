package com.leafaries.financemanagerbackend.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing users.
 * Provides endpoints for retrieving and deleting user information.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    /**
     * Constructs a new UserController with the specified UserService.
     *
     * @param userService the service for managing users
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieved a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @returna a ResponseEntity containg the UserDto if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        logger.debug("Received request to get user with id: {}", id);
        UserDto userDto = userService.getUserById(id);
        logger.debug("Fetched user: {}", userDto);
        return ResponseEntity.ok(userDto);
    }

    /**
     * Delets a user by their ID.
     *
     * @param id the ID of the user to delete
     * @return a ResponseEntity with no content if the deletion is successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.debug("Received request to delete user with id: {}", id);
        userService.deleteUser(id);
        logger.debug("User deleted with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}
