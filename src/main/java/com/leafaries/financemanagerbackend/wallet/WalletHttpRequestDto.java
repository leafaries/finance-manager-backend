package com.leafaries.financemanagerbackend.wallet;

import com.leafaries.financemanagerbackend.currencyexchange.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for wallet HTTP requests.
 * Contains the necessary fields for wallet creation and updates.
 */
public class WalletHttpRequestDto {

    /**
     * The default currency for wallet operations.
     */
    public static final Currency DEFAULT_CURRENCY = Currency.USD;

    /**
     * The name of the wallet.
     * Cannot be blank.
     */
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 255)
    private String name;

    /**
     * The balance of the wallet.
     */
    private double balance;

    /**
     * The currency of the wallet.
     */
    private Currency currency;

    /**
     * Default constructor.
     */
    public WalletHttpRequestDto() {
//        this("", 0, DEFAULT_CURRENCY);
        // Default constructor for deserialization
    }

    /**
     * Constructs a new WalletHttpRequestDto with the specified details, using a String for currency.
     *
     * @param name the name of the wallet
     * @param balance the balance of the wallet
     * @param currency the currency of the wallet
     */
    public WalletHttpRequestDto(String name, double balance, String currency) {
        this(name, balance, Currency.valueOf(currency));
    }

    /**
     * Constructs a new WalletHttpRequestDto with the specified details.
     *
     * @param name the name of the wallet
     * @param balance the balance of the wallet
     * @param currency the currency of the wallet
     */
    public WalletHttpRequestDto(String name, double balance, Currency currency) {
        this.name = (name == null) ? "" : name;
        this.balance = balance;
        this.currency = (currency == null) ? DEFAULT_CURRENCY : currency;
    }

    // Getters and setters

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
}
