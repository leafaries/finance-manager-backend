package com.leafaries.financemanagerbackend.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDto {

    private Long id;
    private Long walletId;
    private BigDecimal amount;
    private LocalDate date;
    private String category;
    private String notes;

    // Default constructor
    public TransactionDto() { }

    // Parameterized constructor
    public TransactionDto(Long id, Long walletId, BigDecimal amount, LocalDate date, String category, String notes) {
        this.id = id;
        this.walletId = walletId;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.notes = notes;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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

    // Optional: toString() method for debugging/logging
    @Override
    public String toString() {
        return "TransactionDto{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", notes='" + notes + '\'' +
                ", walletId=" + walletId +
                '}';
    }
}
