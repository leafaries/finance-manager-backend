package com.leafaries.financemanagerbackend.security;

import com.leafaries.financemanagerbackend.user.User;
import com.leafaries.financemanagerbackend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Utility class for security-related operations.
 * Provides methods for authorization and authentication checks.
 */
@Component
@AllArgsConstructor
public class SecurityUtils {
    private final UserRepository userRepository;

    /**
     * Retrieves the currently authenticated user.
     *
     * @return the currently authenticated user
     */
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        }
        throw new IllegalArgumentException("User not authenticated");
    }
}
