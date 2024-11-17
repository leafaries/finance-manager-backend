package com.leafaries.financemanagerbackend.wallet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateWalletDto {

    public static final String DEFAULT_CURRENCY = "USD";

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 255)
    private String name;

    private double balance;

    @NotBlank(message = "Currency cannot be blank")
    @Size(min = 3, max = 3)
    private String currency;

    public CreateWalletDto() {
        this("", 0, DEFAULT_CURRENCY);
    }

    public CreateWalletDto(String name, double balance, String currency) {
        this.name = (name == null) ? "" : name;
        this.balance = balance;
        this.currency = (currency == null) ? DEFAULT_CURRENCY : currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
