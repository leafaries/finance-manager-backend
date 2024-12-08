package com.leafaries.financemanagerbackend.wallet;

import com.leafaries.financemanagerbackend.currencyexchange.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for wallet entity operations.
 * Extends JpaRepository to provide CRUD operations.
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    /**
     * Finds wallets by their currency.
     *
     * @param currency the currency to search for
     * @return a list of wallets with the specified currency
     */
    List<Wallet> findByCurrency(Currency currency);

    /**
     * Finds all wallets associated with the specified user ID.
     *
     * @param id the ID of the user
     * @return a list of wallets associated with the specified user ID
     */
    List<Wallet> findAllByUserId(Long id);

}
