/**
 * This package contains the main components for managing wallet information
 * within the Finance Manager Backend application, including entities, DTOs,
 * repositories, services, and controllers.
 *
 * <p>Classes in this package:</p>
 * <ul>
 *   <li>{@link com.leafaries.financemanagerbackend.wallet.Wallet} - An entity class representing a wallet. Maps to a database table for wallet storage.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.wallet.WalletHttpRequestDto} - A DTO for wallet HTTP requests. Contains fields for wallet creation and updates.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.wallet.WalletResponseDto} - A DTO for wallet HTTP responses. Contains fields returned to the client.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.wallet.WalletRepository} - A repository interface for wallet entity operations. Extends JpaRepository for CRUD operations.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.wallet.WalletService} - A service class for managing wallet operations. Provides methods to create, retrieve, update, and delete wallets.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.wallet.WalletController} - A controller for managing wallet operations. Provides endpoints to create, read, update, and delete wallet information.</li>
 * </ul>
 *
 * <p>The package structure follows a clear separation of concerns:</p>
 * <ul>
 *   <li><strong>Entities:</strong> {@code Wallet} - Represents the wallet entity in the system.</li>
 *   <li><strong>DTOs:</strong> {@code WalletHttpRequestDto, WalletResponseDto} - Used for data transfer in HTTP requests and responses.</li>
 *   <li><strong>Repositories:</strong> {@code WalletRepository} - Used for performing database operations on wallet entities.</li>
 *   <li><strong>Services:</strong> {@code WalletService} - Contains business logic for wallet management.</li>
 *   <li><strong>Controllers:</strong> {@code WalletController} - Exposes REST endpoints for wallet management.</li>
 * </ul>
 */
package com.leafaries.financemanagerbackend.wallet;
