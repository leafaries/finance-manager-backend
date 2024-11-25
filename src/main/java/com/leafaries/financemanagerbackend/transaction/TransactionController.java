package com.leafaries.financemanagerbackend.transaction;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing financial transactions.
 * Provides endpoints for creating, retrieving, updating, and deleting transactions.
 */
@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
@Slf4j
public class TransactionController {
    private final TransactionService transactionService;

    /**
     * Creates a new transaction.
     *
     * @param transactionCreateDto the data transfer object containing transaction details for creation
     * @return a {@code ResponseEntity} containing the created transaction
     */
    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionCreateDto transactionCreateDto) {
        log.debug("Received request to create transaction with data: {}", transactionCreateDto);
        TransactionDto createdTransaction = transactionService.createTransaction(transactionCreateDto);
        log.debug("Created transaction: {}", transactionCreateDto);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    /**
     * Retrieves all transactions.
     *
     * @return a {@code ResponseEntity} containing a list of all transactions
     */
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        log.debug("Fetching all transactions via controller");
        List<TransactionDto> transactions = transactionService.getAllTransactions();
        log.debug("Fetched transactions: {}", transactions);
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
        log.debug("Received request to fetch transaction with id: {}", id);
        TransactionDto transactionDto = transactionService.getTransactionById(id);
        log.debug("Fetched transaction: {}", id);
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
        log.debug("Received request to update transaction with id: {} and data: {}", id, transactionCreateDto);
        TransactionDto updatedTransaction = transactionService.updateTransaction(id, transactionCreateDto);
        log.debug("Updated transaction: {}", updatedTransaction);
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
        log.debug("Received request to delete transaction with id: {}", id);
        boolean isDeleted = transactionService.deleteTransaction(id);
        if (isDeleted) {
            log.debug("Transaction deleted with id: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            log.debug("Transaction not found for id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
