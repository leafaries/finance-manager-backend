package com.leafaries.financemanagerbackend.wallet;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) for wallet HTTP responses.
 * Contains the necessary fields returned to the client.
 */
public class WalletResponseDto {

    /**
     * The ID of the wallet.
     */
    private Long id;

    /**
     * The name of the wallet.
     */
    private String name;

    /**
     * The balance of the wallet.
     */
    private BigDecimal balance;

    /**
     * The currency of the wallet.
     */
    private String currency;

    /**
     * Default constructor.
     */
    public WalletResponseDto() {
        // Default constructor for deserialization
    }

    /**
     * Constructs a new WalletResponseDto with the specified details.
     *
     * @param id the ID of the wallet
     * @param name the name of the wallet
     * @param balance the balance of the wallet
     * @param currency the currency of the wallet
     */
    public WalletResponseDto(Long id, String name, BigDecimal balance, String currency) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.currency = currency;
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
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the wallet.
     *
     * @param balance the balance of the wallet
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Gets the currency of the wallet.
     *
     * @return the currency of the wallet
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the currency of the wallet.
     *
     * @param currency the currency of the wallet
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
