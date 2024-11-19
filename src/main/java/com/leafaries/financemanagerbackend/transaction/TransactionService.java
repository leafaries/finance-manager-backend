package com.leafaries.financemanagerbackend.transaction;

import com.leafaries.financemanagerbackend.wallet.Wallet;
import com.leafaries.financemanagerbackend.wallet.WalletRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing financial transactions.
 * Provides business logic for creating, retrieving, updating, and deleting transactions.
 */
@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new {@code TransactionService} with the specified repositories and model mapper.
     *
     * @param transactionRepository the repository for managing transactions
     * @param walletRepository the repository for managing wallets
     * @param modelMapper the model mapper for converting entities and DTOs
     */
    public TransactionService(TransactionRepository transactionRepository,
                              WalletRepository walletRepository,
                              ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Creates a new transaction.
     *
     * @param transactionCreateDto the data transfer object containing transaction details for creation
     * @return the created transaction as a {@code TransactionDto}
     */
    public TransactionDto createTransaction(TransactionCreateDto transactionCreateDto) {
        logger.debug("Creating transaction with data: {}", transactionCreateDto);
        Wallet wallet = getWalletById(transactionCreateDto.getWalletId());
        Transaction transaction = modelMapper.map(transactionCreateDto, Transaction.class);
        transaction.setWallet(wallet);
        transaction.setAmount(BigDecimal.valueOf(transactionCreateDto.getAmount()));
        logger.debug("Mapped transaction entity: {}", transaction);
        Transaction savedTransaction = transactionRepository.save(transaction);
        logger.debug("Saved transaction entity: {}", savedTransaction);
        return modelMapper.map(savedTransaction, TransactionDto.class);
    }

    /**
     * Retrieves all transactions.
     *
     * @return a list of all transactions as {@code TransactionDto}
     */
    public List<TransactionDto> getAllTransactions() {
        logger.debug("Fetching all transactions");
        List<Transaction> transactions = transactionRepository.findAll();
        logger.debug("Found {} transactions", transactions);
        List<TransactionDto> transactionDtos = transactions.stream()
                .map(transaction -> {
                    TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
                    logger.debug("Mapped transaction: {}", transactionDto);
                    return transactionDto;
                })
                .toList();
        logger.debug("Final transaction DTO list: {}", transactionDtos);
        return transactionDtos;
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param id the ID of the transaction to retrieve
     * @return the retrieved transaction as a {@code TransactionDto}
     */
    public TransactionDto getTransactionById(Long id) {
        logger.debug("Fetching transaction with id: {}", id);
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            logger.debug("Found transaction: {}", transaction.get());
            return modelMapper.map(transaction.get(), TransactionDto.class);
        } else {
            logger.debug("Transaction not found for id: {}", id);
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
        logger.debug("Updating transaction with id: {} and data: {}", id, transactionCreateDto);
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));
        Wallet wallet = getWalletById(transactionCreateDto.getWalletId());
        modelMapper.map(transactionCreateDto, transaction);
        transaction.setWallet(wallet);
        transaction.setAmount(BigDecimal.valueOf(transactionCreateDto.getAmount()));
        logger.debug("Updating transaction entity: {}", transaction);
        Transaction updatedTransaction = transactionRepository.save(transaction);
        logger.debug("Updated transaction entity: {}", updatedTransaction);
        return modelMapper.map(updatedTransaction, TransactionDto.class);
    }

    /**
     * Deletes a transaction by its ID.
     *
     * @param id the ID of the transaction to delete
     * @return {@code true} if the transaction was successfully deleted; {@code false} otherwise
     */
    public boolean deleteTransaction(Long id) {
        logger.debug("Deleting transaction with id: {}", id);
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
            logger.debug("Transaction deleted successfully");
            return true;
        }
        logger.debug("Transaction not found for id: {}", id);
        return false;
    }

    /**
     * Retrieves a wallet by its ID.
     *
     * @param walletId the ID of the wallet to retrieve
     * @return the retrieved wallet
     */
    private Wallet getWalletById(Long walletId) {
        logger.debug("Fetching wallet with id: {}", walletId);
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found"));
    }
}
