package com.leafaries.financemanagerbackend.security;

import com.leafaries.financemanagerbackend.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class for loading user-specific data.
 * Implements the {@link UserDetailsService} interface provided by Spring Security.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructs a new {@code CustomUserDetailsService} with the specified {@code UserRepository}.
     *
     * @param userRepository the repository for accessing user data
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads a user by their username.
     * This method is called by Spring Security to authenticate a user.
     *
     * @param username the username identifying the user whose data is required
     * @return a fully populated user record
     * @throws UsernameNotFoundException if the user could not be found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
