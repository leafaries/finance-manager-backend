package com.leafaries.financemanagerbackend.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing users.
 * Provides endpoints for retrieving and deleting user information.
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Retrieved a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return a ResponseEntity containg the UserDto if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        log.debug("Received request to get user with id: {}", id);
        UserDto userDto = userService.getUserById(id);
        log.debug("Fetched user: {}", userDto);
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
        log.debug("Received request to delete user with id: {}", id);
        userService.deleteUser(id);
        log.debug("User deleted with id: {}", id);
        return ResponseEntity.noContent().build();
    }

}
