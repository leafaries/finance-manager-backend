/**
 * This package contains the main components for managing user information
 * within the Finance Manager Backend application, including entities, DTOs, repositories,
 * services, and controllers.
 *
 * <p>Classes in this package:</p>
 * <ul>
 *   <li>{@link com.leafaries.financemanagerbackend.user.User} - An entity class representing a user. Implements {@code UserDetails} for Spring Security integration.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.user.UserDto} - A DTO for transferring user information between different layers of the application.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.user.UserRegistrationDto} - A DTO for transferring user registration information.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.user.UserLoginDto} - A DTO for transferring user login information.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.user.UserController} - A REST controller for managing users. Provides endpoints for retrieving and deleting user information.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.user.UserService} - A service class for managing users. Provides methods for registering, retrieving, and deleting user information.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.user.UserRepository} - A repository interface for managing User entities. Provides methods for performing database operations on User entities.</li>
 * </ul>
 *
 * <p>The package structure follows a clear separation of concerns:</p>
 * <ul>
 *   <li><strong>Entities:</strong> {@code User} - Represents the user in the system, including integration with Spring Security.</li>
 *   <li><strong>DTOs:</strong> {@code UserDto, UserRegistrationDto, UserLoginDto} - Used for data transfer between different layers of the application.</li>
 *   <li><strong>Repositories:</strong> {@code UserRepository} - Used for performing database operations.</li>
 *   <li><strong>Services:</strong> {@code UserService} - Contains business logic for user management.</li>
 *   <li><strong>Controllers:</strong> {@code UserController} - Exposes REST endpoints for user management.</li>
 * </ul>
 */
package com.leafaries.financemanagerbackend.user;
