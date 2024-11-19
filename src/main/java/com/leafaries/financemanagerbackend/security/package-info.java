/**
 * This package contains the security-related components for the application.
 * It includes classes responsible for handling authentication, user details
 * services, JWT utilities, filters, and security operations.
 *
 * <p>
 * Main components in this package:
 * </p>
 *
 * <ul>
 *   <li>{@link com.leafaries.financemanagerbackend.security.AuthController}: REST controller for handling authentication-related operations.
 *       Provides endpoints for user registration and login.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.security.CustomUserDetailsService}: Service class for loading user-specific data.
 *       Implements the {@link org.springframework.security.core.userdetails.UserDetailsService} interface provided by Spring Security.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.security.JwtRequestFilter}: Filter class for processing JWT authentication tokens.
 *       This filter intercepts every request to validate the JWT token in the Authorization header.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.security.JwtUtil}: Utility class for handling JSON Web Tokens (JWT).
 *       Provides methods for generating, validating, and extracting information from JWT tokens.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.security.SecurityUtils}: Utility class for security-related operations.
 *       Provides methods for authorization and authentication checks.</li>
 * </ul>
 *
 * <p>
 * These components work together to provide a comprehensive security framework for the application,
 * ensuring secure authentication and authorization mechanisms are in place.
 * </p>
 *
 * @see com.leafaries.financemanagerbackend.security.AuthController
 * @see com.leafaries.financemanagerbackend.security.CustomUserDetailsService
 * @see com.leafaries.financemanagerbackend.security.JwtRequestFilter
 * @see com.leafaries.financemanagerbackend.security.JwtUtil
 * @see com.leafaries.financemanagerbackend.security.SecurityUtils
 */
package com.leafaries.financemanagerbackend.security;