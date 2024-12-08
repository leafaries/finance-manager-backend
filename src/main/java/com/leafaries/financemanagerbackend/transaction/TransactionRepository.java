package com.leafaries.financemanagerbackend.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for managing financial transactions.
 * Provides methods for performing CRUD operations and custom queries on transactions.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Finds all transactions within a specific wallet by wallet ID.
     *
     * @param walletId the ID of the wallet
     * @return a list of transactions within the specified wallet
     */
    List<Transaction> findByWalletId(Long walletId);

    /**
     * Finds all transactions within a date range for a specific wallet by wallet ID.
     *
     * @param walletId the ID of the wallet
     * @param startDate the start date of the date range
     * @param endDate the end date of the date range
     * @return a list of transactions within the specified date range and wallet
     */
    List<Transaction> findByWalletIdAndDateBetween(Long walletId, LocalDate startDate, LocalDate endDate);

    /**
     * Finds transactions within a specific wallet by category.
     *
     * @param walletId the ID of the wallet
     * @param category the category of the transactions
     * @return a list of transactions within the specified wallet and category
     */
    List<Transaction> findByWalletIdAndCategory(Long walletId, String category);

    /**
     * Finds transactions within a specific wallet by amount range.
     *
     * @param walletId the ID of the wallet
     * @param minAmount the minimum amount of the transactions
     * @param maxAmount the maximum amount of the transactions
     * @return a list of transactions within the specified wallet and amount range
     */
    List<Transaction> findByWalletIdAndAmountBetween(Long walletId, BigDecimal minAmount, BigDecimal maxAmount);

}
