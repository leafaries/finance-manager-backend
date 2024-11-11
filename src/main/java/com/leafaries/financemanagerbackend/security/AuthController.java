package com.leafaries.financemanagerbackend.security;

import com.leafaries.financemanagerbackend.user.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager,
                          CustomUserDetailsService userDetailsService,
                          JwtUtil jwtUtil,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationDto userRegistrationDto, BindingResult result) {
        // Check for validation errors
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation erros: " + result.getAllErrors());
        }

        if (userService.getUserByEmail(userRegistrationDto.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken");
        }

        if (userService.getUserByEmail(userRegistrationDto.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use");
        }

        userService.registerUser(userRegistrationDto);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
//    @PostMapping("/authenticate")
//    public ResponseEntity<String> createAuthenticationToken(@RequestBody UserDto userDto) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
//        } catch (Exception ex) {
//            throw new Exception("Invalid username or password", ex);
//        }
//
//        UserDetails user = userDetailsService.loadUserByUsername(userDto.getUsername());
//        String jwt = jwtUtil.generateToken(user.getUsername());
//        return ResponseEntity.ok(jwt);
//    }
        String identifier = userLoginDto.getIdentifier();
        String password = userLoginDto.getPassword();

        Optional<UserDto> userOptional;

        if (identifier.contains("@")) {
            // Assume it's an email
//            userOptional = userDetailsService.loadUserByUsername(identifier);
        } else {
            // Otherwise, assume it's a username
            userOptional = userService.getUserByUsername(identifier);
        }

//        if (userOptional.isEmpty() || !passwordEncoder.matches(password, userOptional.get().getPassword())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
//        }

        // Generate JWT token and return it
//        String token = jwtUtil.generateToken(String.valueOf(userOptional.get()));
//        return ResponseEntity.ok(new JwtResponse(token));
//        return ResponseEntity.ok("User logged in successfully");
        return null;
    }
}
