package com.leafaries.financemanagerbackend.wallet;

import java.math.BigDecimal;

public class WalletDto {

    private Long id;
    private String name;
    private BigDecimal balance;
    private String currency;

    // Default constructor
    public WalletDto() {}

    // Parameterized constructor
    public WalletDto(Long id, String name, BigDecimal balance, String currency) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
