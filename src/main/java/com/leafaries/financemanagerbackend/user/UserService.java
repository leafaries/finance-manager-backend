package com.leafaries.financemanagerbackend.user;

import com.leafaries.financemanagerbackend.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing users.
 * Provides methods for registering, retrieving, and deleting user information.
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user.
     *
     * @param userRegistrationDto the data transfer object containing user registration details
     * @return the registered UserDto
     */
    public UserDto registerUser(UserRegistrationDto userRegistrationDto) {
        log.debug("Registering new user with data: {}", userRegistrationDto.getUsername());
        User user = modelMapper.map(userRegistrationDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt the password
        User savedUser = userRepository.save(user);
        log.debug("Saved user entity: {}", savedUser.getId());
        return modelMapper.map(savedUser, UserDto.class);
    }

    /**
     * Retrieved a user by their username.
     *
     * @param username the username of the user to retrieve
     * @return an Optional containing the UserDto if found
     */
    public Optional<UserDto> getUserByUsername(String username) {
        log.debug("Fetching user with username: {}", username);
        Optional<User> user = userRepository.findByUsername(username);
        log.debug("Fetched user entity for username: {}", user.isPresent() ? user.get().getUsername() : "not found");
        Optional<UserDto> userDto = user.map(u -> modelMapper.map(u, UserDto.class));
        return userDto;
    }

    /**
     * Retrieved a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the UserDto of the found user
     */
    public UserDto getUserById(Long id) {
        log.debug("Fetching user with id: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        log.debug("Fetched user entity with id: {}", user.getId());
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    public void deleteUser(Long id) {
        log.debug("Deleting user with id: {}", id);
        userRepository.findById(id).ifPresent(userRepository::delete);
        log.debug("Deleted user with id: {}", id);
    }
}
