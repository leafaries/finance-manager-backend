package com.leafaries.financemanagerbackend.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing financial transactions.
 * Provides endpoints for creating, retrieving, updating, and deleting transactions.
 */
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;

    /**
     * Constructs a new {@code TransactionController} with the specified {@code TransactionService}.
     *
     * @param transactionService the service for managing transactions
     */
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Creates a new transaction.
     *
     * @param transactionCreateDto the data transfer object containing transaction details for creation
     * @return a {@code ResponseEntity} containing the created transaction
     */
    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionCreateDto transactionCreateDto) {
        logger.debug("Received request to create transaction with data: {}", transactionCreateDto);
        TransactionDto createdTransaction = transactionService.createTransaction(transactionCreateDto);
        logger.debug("Created transaction: {}", transactionCreateDto);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    /**
     * Retrieves all transactions.
     *
     * @return a {@code ResponseEntity} containing a list of all transactions
     */
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        logger.debug("Fetching all transactions via controller");
        List<TransactionDto> transactions = transactionService.getAllTransactions();
        logger.debug("Fetched transactions: {}", transactions);
        return ResponseEntity.ok(transactions);
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param id the ID of the transaction to retrieve
     * @return a {@code ResponseEntity} containing the retrieved transaction
     */
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        logger.debug("Received request to fetch transaction with id: {}", id);
        TransactionDto transactionDto = transactionService.getTransactionById(id);
        logger.debug("Fetched transaction: {}", id);
        return transactionDto != null ? ResponseEntity.ok(transactionDto) : ResponseEntity.notFound().build();
    }

    /**
     * Updates an existing transaction.
     *
     * @param id the ID of the transaction to update
     * @param transactionCreateDto the data transfer object containing the new transaction details
     * @return a {@code ResponseEntity} containing the updated transaction
     */
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id,
                                                            @RequestBody TransactionCreateDto transactionCreateDto) {
        logger.debug("Received request to update transaction with id: {} and data: {}", id, transactionCreateDto);
        TransactionDto updatedTransaction = transactionService.updateTransaction(id, transactionCreateDto);
        logger.debug("Updated transaction: {}", updatedTransaction);
        return ResponseEntity.ok(updatedTransaction);
    }

    /**
     * Deletes a transaction by its ID.
     *
     * @param id the ID of the transaction to delete
     * @return a {@code ResponseEntity} with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        logger.debug("Received request to delete transaction with id: {}", id);
        boolean isDeleted = transactionService.deleteTransaction(id);
        if (isDeleted) {
            logger.debug("Transaction deleted with id: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.debug("Transaction not found for id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
