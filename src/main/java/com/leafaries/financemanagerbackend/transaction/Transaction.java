package com.leafaries.financemanagerbackend.transaction;

import com.leafaries.financemanagerbackend.wallet.Wallet;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String category;

    @Column(length = 500)
    private String notes;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    // Default constructor
    public Transaction() {}

    // Parameterized constructor
    public Transaction(BigDecimal amount, LocalDate date, String category, String notes, Wallet wallet) {
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.notes = notes;
        this.wallet = wallet;
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

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    // Optional: toString() method for debugging/logging
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", notes='" + notes + '\'' +
                ", wallet=" + (wallet != null ? wallet.getId() : null) +
                '}';
    }
}

//@Entity
//public class Transaction {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private BigDecimal amount;
//    private LocalDate date;
//    private String notes;
//
//    @Enumerated(EnumType.STRING)
//    private TransactionType type; // Income or Expense
//
//    @ManyToOne
//    private Wallet wallet;
//
//    @ManyToOne
//    private Category category;
//
//    // Constructors, getters and setters
//}
