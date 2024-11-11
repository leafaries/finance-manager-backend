package com.leafaries.financemanagerbackend.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDto {

    private Long id;
    private BigDecimal amount;
    private LocalDate date;
    private String category;
    private String notes;
    private Long walletId;

    // Default constructor
    public TransactionDto() {}

    // Parameterized constructor
    public TransactionDto(Long id, BigDecimal amount, LocalDate date, String category, String notes, Long walletId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.notes = notes;
        this.walletId = walletId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", notes='" + notes + '\'' +
                ", walletId=" + walletId +
                '}';
    }
}
