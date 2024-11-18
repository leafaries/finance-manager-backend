package com.leafaries.financemanagerbackend.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionCreateDto transactionCreateDto) {
        logger.debug("Received request to create transaction with data: {}", transactionCreateDto);
        TransactionDto createdTransaction = transactionService.createTransaction(transactionCreateDto);
        logger.debug("Created transaction: {}", transactionCreateDto);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        logger.debug("Received request to fetch transaction with id: {}", id);
        TransactionDto transactionDto = transactionService.getTransactionById(id);
        logger.debug("Fetched transaction: {}", id);
        return transactionDto != null ? ResponseEntity.ok(transactionDto) : ResponseEntity.notFound().build();
    }

    // Currently the TransactionDto in a list has an id = wallet_id, instead of ther real one fix it
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        logger.debug("Fetching all transactions via controller");
        List<TransactionDto> transactions = transactionService.getAllTransactions();
        logger.debug("Fetched transactions: {}", transactions);
        return ResponseEntity.ok(transactions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable Long id,
                                                            @RequestBody TransactionCreateDto transactionCreateDto) {
        logger.debug("Received request to update transaction with id: {} and data: {}", id, transactionCreateDto);
        TransactionDto updatedTransaction = transactionService.updateTransaction(id, transactionCreateDto);
        logger.debug("Updated transaction: {}", updatedTransaction);
        return ResponseEntity.ok(updatedTransaction);
    }

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
