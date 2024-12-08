package com.leafaries.financemanagerbackend.transaction;

import com.leafaries.financemanagerbackend.wallet.Wallet;
import com.leafaries.financemanagerbackend.wallet.WalletRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing financial transactions.
 * Provides business logic for creating, retrieving, updating, and deleting transactions.
 */
@Slf4j
@AllArgsConstructor
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;

    /**
     * Creates a new transaction.
     *
     * @param transactionCreateDto the data transfer object containing transaction details for creation
     * @return the created transaction as a {@code TransactionDto}
     */
    public TransactionDto createTransaction(TransactionCreateDto transactionCreateDto) {
        log.debug("Creating transaction with data: {}", transactionCreateDto);
        Wallet wallet = getWalletById(transactionCreateDto.getWalletId());
        Transaction transaction = modelMapper.map(transactionCreateDto, Transaction.class);
        transaction.setWallet(wallet);
        transaction.setAmount(BigDecimal.valueOf(transactionCreateDto.getAmount()));
        log.debug("Mapped transaction entity: {}", transaction);
        Transaction savedTransaction = transactionRepository.save(transaction);
        log.debug("Saved transaction entity: {}", savedTransaction);
        return modelMapper.map(savedTransaction, TransactionDto.class);
    }

    /**
     * Retrieves all transactions.
     *
     * @return a list of all transactions as {@code TransactionDto}
     */
    public List<TransactionDto> getAllTransactions() {
        log.debug("Fetching all transactions");
        List<Transaction> transactions = transactionRepository.findAll();
        log.debug("Found {} transactions", transactions);
        List<TransactionDto> transactionDtos = transactions.stream()
                .map(transaction -> {
                    TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
                    log.debug("Mapped transaction: {}", transactionDto);
                    return transactionDto;
                })
                .toList();
        log.debug("Final transaction DTO list: {}", transactionDtos);
        return transactionDtos;
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param id the ID of the transaction to retrieve
     * @return the retrieved transaction as a {@code TransactionDto}
     */
    public TransactionDto getTransactionById(Long id) {
        log.debug("Fetching transaction with ID: {}", id);
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            log.debug("Found transaction: {}", transaction.get());
            return modelMapper.map(transaction.get(), TransactionDto.class);
        } else {
            log.debug("Transaction not found for ID: {}", id);
            return null;
        }
    }

    /**
     * Updates an existing transaction.
     *
     * @param id the ID of the transaction to update
     * @param transactionCreateDto the data transfer object containing the new transaction details
     * @return the updated transaction as a {@code TransactionDto}
     */
    public TransactionDto updateTransaction(Long id, TransactionCreateDto transactionCreateDto) {
        log.debug("Updating transaction with ID: {} and data: {}", id, transactionCreateDto);
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
        Wallet wallet = getWalletById(transactionCreateDto.getWalletId());
        modelMapper.map(transactionCreateDto, transaction);
        transaction.setWallet(wallet);
        transaction.setAmount(BigDecimal.valueOf(transactionCreateDto.getAmount()));
        log.debug("Updating transaction entity: {}", transaction);
        Transaction updatedTransaction = transactionRepository.save(transaction);
        log.debug("Updated transaction entity: {}", updatedTransaction);
        return modelMapper.map(updatedTransaction, TransactionDto.class);
    }

    /**
     * Deletes a transaction by its ID.
     *
     * @param id the ID of the transaction to delete
     * @return {@code true} if the transaction was successfully deleted; {@code false} otherwise
     */
    public boolean deleteTransaction(Long id) {
        log.debug("Deleting transaction with id: {}", id);
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            log.debug("Transaction deleted successfully");
            return true;
        }
        log.debug("Transaction not found for ID: {}", id);
        return false;
    }

    /**
     * Retrieves a wallet by its ID.
     *
     * @param walletId the ID of the wallet to retrieve
     * @return the retrieved wallet
     */
    private Wallet getWalletById(Long walletId) {
        log.debug("Fetching wallet with ID: {}", walletId);
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found"));
    }

}
