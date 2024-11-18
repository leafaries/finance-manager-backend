package com.leafaries.financemanagerbackend.transaction;

import java.time.LocalDate;

public class TransactionCreateDto  {

    private double amount;
    private Long walletId;
    private LocalDate date;
    private String category;
    private String notes;

    // Default constructor
    public TransactionCreateDto() { }

    // Parameterized constructor
    public TransactionCreateDto(double amount,
                                LocalDate date,
                                String category,
                                String notes,
                                Long walletId) {
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.notes = notes;
        this.walletId = walletId;
    }

    // Getters and Setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getNotes() {
        return notes;
    }
    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    // Optional: toString() method for debugging/logging
    @Override
    public String toString() {
        return "TransactionDto{" +
                "amount=" + amount +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", notes='" + notes + '\'' +
                ", walletId=" + walletId +
                '}';
    }
}
