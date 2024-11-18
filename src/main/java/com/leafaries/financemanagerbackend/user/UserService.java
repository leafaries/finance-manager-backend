package com.leafaries.financemanagerbackend.user;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto registerUser(UserRegistrationDto userRegistrationDto) {
        logger.debug("Registering new user with data: {}", userRegistrationDto.getUsername());
        User user = modelMapper.map(userRegistrationDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt the password
        User savedUser = userRepository.save(user);
        logger.debug("Saved user entity: {}", savedUser.getId());
        return modelMapper.map(savedUser, UserDto.class);
    }

    public Optional<UserDto> getUserByUsername(String username) {
        logger.debug("Fetching user with username: {}", username);
        Optional<User> user = userRepository.findByUsername(username);
        logger.debug("Fetched user entity for username: {}", user.isPresent() ? user.get().getUsername() : "not found");
        Optional<UserDto> userDto = user.map(u -> modelMapper.map(u, UserDto.class));
        return userDto;
    }

    public UserDto getUserById(Long id) {
        logger.debug("Fetching user with id: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        logger.debug("Fetched user entity with id: {}", user.getId());
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public void deleteUser(Long id) {
        logger.debug("Deleting user with id: {}", id);
        userRepository.findById(id).ifPresent(userRepository::delete);
        logger.debug("Deleted user with id: {}", id);
    }
}
