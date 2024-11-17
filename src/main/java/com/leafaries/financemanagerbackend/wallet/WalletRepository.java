package com.leafaries.financemanagerbackend.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findByCurrency(String currency); // Custom query to find wallets by currency
    List<Wallet> findAllByUserId(Long id);
}