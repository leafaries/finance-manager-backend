package com.leafaries.financemanagerbackend.transaction;

import com.leafaries.financemanagerbackend.wallet.Wallet;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity class representing a financial transaction.
 * Maps to database table for transaction storage.
 */
@Entity
@Table(name = "transactions")
public class Transaction {

    /**
     * The ID of the transaction.
     * Generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The amount of money involved in the transaction.
     * Cannot be null.
     */
    @Column(nullable = false)
    private BigDecimal amount;

    /**
     * The date when the transaction occurred.
     * Cannot be null.
     */
    @Column(nullable = false)
    private LocalDate date;

    /**
     * The category of the transaction (e.g., groceries, salary).
     * Cannot be null.
     */
    @Column(nullable = false)
    private String category;

    /**
     * Additional notes or description about the transaction.
     * Maximum length is 500 characters.
     */
    @Column(length = 500)
    private String notes;

    /**
     * The wallet associated with the transaction.
     * Cannot be null.
     */
    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    /**
     * Default constructor for JPA.
     */
    public Transaction() { }

    /**
     * Constructs a new Transaction with the specified details.
     *
     * @param amount the amount of money involved in the transaction
     * @param date the date when the transaction occurred
     * @param category the category of the transaction
     * @param notes additional notes or description about the transaction
     * @param wallet the wallet associated with the transaction
     */
    public Transaction(BigDecimal amount, LocalDate date, String category, String notes, Wallet wallet) {
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.notes = notes;
        this.wallet = wallet;
    }

    // Getters and Setters

    /**
     * Gets the ID of the transaction.
     *
     * @return the ID of the transaction
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the transaction.
     *
     * @param id the ID of the transaction
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the amount of money involved in the transaction.
     *
     * @return the amount of money involved in the transaction
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount of money involved in the transaction.
     *
     * @param amount the amount of money involved in the transaction
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the date when the transaction occurred.
     *
     * @return the date when the transaction occurred
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date when the transaction occurred.
     *
     * @param date the date when the transaction occurred
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the category of the transaction.
     *
     * @return the category of the transaction
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the transaction.
     *
     * @param category the category of the transaction
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the additional notes or description about the transaction.
     *
     * @return the additional notes or description about the transaction
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the additional notes or description about the transaction.
     *
     * @param notes the additional notes or description about the transaction
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Gets the wallet associated with the transaction.
     *
     * @return the wallet associated with the transaction
     */
    public Wallet getWallet() {
        return wallet;
    }

    /**
     * Sets the wallet associated with the transaction.
     *
     * @param wallet the wallet associated with the transaction
     */
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    /**
     * Returns a string representation of the transaction for debugging and logging purposes.
     *
     * @return a string representation of the transaction
     */
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
