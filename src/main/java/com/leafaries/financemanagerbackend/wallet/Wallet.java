package com.leafaries.financemanagerbackend.wallet;

import com.leafaries.financemanagerbackend.currencyexchange.Currency;
import com.leafaries.financemanagerbackend.transaction.Transaction;
import com.leafaries.financemanagerbackend.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

// TODO: Changing the currency should change all the stored values to that currency?? Not sure if this's an issue

/**
 * Entity class representing a wallet.
 * Maps to a database table for wallet storage.
 */
@Entity
@Table(name = "wallets")
public class Wallet {

    /**
     * The ID of the wallet.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the wallet.
     * Cannot be blank.
     */
    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String name;

    /**
     * The balance of the wallet.
     */
    @Column(nullable = false)
    private double balance;

    /**
     * The currency of the wallet.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    /**
     * The list of transactions associated with the wallet.
     * Mapped by the "wallet" fields in the Transaction entity.
     * Cascade type ALL and orphan removal enabled.
     */
    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    /**
     * The user who owns the wallet.
     * Mapped to the user_id column in the "users" table.
     */
    @ManyToOne
    @JoinTable(name = "user_id")
    private User user;

    /**
     * Default constructor.
     */
    public Wallet() {
        // Default constructor for JPA
    }

    /**
     * Constructs a new Wallet with the specified details.
     *
     * @param name the name of the wallet
     * @param balance the balance of the wallet
     * @param currency the currency of the wallet
     * @param user the user who owns the wallet
     */
    public Wallet(String name, double balance, Currency currency, User user) {
        this.name = name;
        this.balance = balance;
        this.currency = currency;
        this.user = user;
    }

    // Getters and setters

    /**
     * Gets the ID of the wallet.
     *
     * @return the ID of the wallet
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the wallet.
     *
     * @param id the ID of the wallet
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the wallet.
     *
     * @return the name of the wallet
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the wallet.
     *
     * @param name the name of the wallet
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the balance of the wallet.
     *
     * @return the balance of the wallet
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the wallet.
     *
     * @param balance the balance of the wallet
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the currency of the wallet.
     *
     * @return the currency of the wallet
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Sets the currency of the wallet.
     *
     * @param currency the currency of the wallet
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Gets the list of transactions associated with the wallet.
     *
     * @return the list of transactions
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Sets the list of the transactions associated with the wallet.
     *
     * @param transactions the list of transactions
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Adds a transaction to the wallet.
     *
     * @param transaction the transaction to add
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setWallet(this);
    }

    /**
     * Removes a transaction from the wallet.
     *
     * @param transaction the transaction to remove
     */
    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
        transaction.setWallet(null);
    }

    /**
     * Gets the user who owns the wallet.
     *
     * @return the user who owns the wallet
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who own the wallet.
     *
     * @param user the user who owns the wallet
     */
    public void setUser(User user) {
        this.user = user;
    }
}
