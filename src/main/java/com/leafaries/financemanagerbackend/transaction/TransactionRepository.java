package com.leafaries.financemanagerbackend.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Find all transactions within a wallet by wallet ID
    List<Transaction> findByWalletId(Long walletId);

    // Find all transactions within a date range for a specific wallet
    List<Transaction> findByWalletIdAndDateBetween(Long walletId, LocalDate startDate, LocalDate endDate);

    // Find transactions in a wallet by category
    List<Transaction> findByWalletIdAndCategory(Long walletId, String category);

    // Find transactions in a wallet by amount range
    List<Transaction> findByWalletIdAndAmountBetween(Long walletId, BigDecimal minAmount, BigDecimal maxAmount);
}
