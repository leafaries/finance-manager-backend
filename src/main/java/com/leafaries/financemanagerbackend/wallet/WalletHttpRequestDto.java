package com.leafaries.financemanagerbackend.wallet;

import com.leafaries.financemanagerbackend.currencyexchange.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for wallet HTTP requests.
 * Contains the necessary fields for wallet creation and updates.
 */
@Getter
@Setter
@NoArgsConstructor
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
}
