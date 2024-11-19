package com.leafaries.financemanagerbackend.security;

import com.leafaries.financemanagerbackend.user.*;
import com.leafaries.financemanagerbackend.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling authentication-related operations.
 * Provides endpoints for user registration and login.
 */
@RestController("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    /**
     * Constructs a new {@code AuthController} with the specified services and utilities.
     *
     * @param authenticationManager the authentication manager for handling authentication
     * @param userDetailsService the service for loading user details
     * @param jwtUtil the utility for handling JWT operations
     * @param userService the service for user-related operations
     */
    public AuthController(AuthenticationManager authenticationManager,
                          CustomUserDetailsService userDetailsService,
                          JwtUtil jwtUtil,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    /**
     * Registers a new user.
     *
     * @param userRegistrationDto the data transfer object containing registration details
     * @param result the binding result for validating the registration data
     * @return a {@code ResponseEntity} indicating the result of the registration process
     */
    @PostMapping("/api/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationDto userRegistrationDto,
                                      BindingResult result) {
        // Check for validation errors
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation erros: " + result.getAllErrors());
        }

        if (userService.getUserByUsername(userRegistrationDto.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken");
        }

        userService.registerUser(userRegistrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param userLoginDto the data transfer object containing login details
     * @return a {@code ResponseEntity} containing the JWT token
     */
    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (userLoginDto.getUsername(), userLoginDto.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
        }

        UserDetails user = userDetailsService.loadUserByUsername(userLoginDto.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(token);
    }
}
