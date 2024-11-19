/**
 * The package contains all the classes and interfaces related to the management of financial transactions.
 * This includes Data Transfer Objects (DTOs), the main Transaction entity, service layer, repository
 * interface, and the REST controller for handling HTTP requests.
 *
 * <p>
 * The main components in this package are:
 * </p>
 *
 * <ul>
 *   <li>{@link com.leafaries.financemanagerbackend.transaction.Transaction}: Represents the main entity for a financial transaction.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.transaction.TransactionCreateDto}: DTO used for creating new transactions.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.transaction.TransactionDto}: DTO used for transferring transaction information.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.transaction.TransactionRepository}: Repository interface for transaction persistence operations.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.transaction.TransactionService}: Service class providing business logic for transactions.</li>
 *   <li>{@link com.leafaries.financemanagerbackend.transaction.TransactionController}: REST controller for exposing transaction-related endpoints.</li>
 * </ul>
 *
 * <p>
 * These components collaborate to provide a complete MVC-based backend for managing financial transactions.
 * </p>
 *
 * @see com.leafaries.financemanagerbackend.transaction.Transaction
 * @see com.leafaries.financemanagerbackend.transaction.TransactionCreateDto
 * @see com.leafaries.financemanagerbackend.transaction.TransactionDto
 * @see com.leafaries.financemanagerbackend.transaction.TransactionRepository
 * @see com.leafaries.financemanagerbackend.transaction.TransactionService
 * @see com.leafaries.financemanagerbackend.transaction.TransactionController
 */
package com.leafaries.financemanagerbackend.transaction;