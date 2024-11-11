package com.leafaries.financemanagerbackend.security;

import com.leafaries.financemanagerbackend.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

//    @Override
//    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
//    }
}