package com.leafaries.financemanagerbackend.transaction;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for creating a new financial transaction.
 * Contains the necessary fields required for transaction creation.
 */
public class TransactionCreateDto  {

    /**
     * The amount of money involved in the transaction.
     */
    private double amount;

    /**
     * The ID of the wallet associated with the transaction.
     */
    private Long walletId;

    /**
     * The date when the transaction occurred.
     */
    private LocalDate date;

    // TODO: Convert String to Category
    /**
     * The category of the transaction (e.g., groceries, salary).
     */
    private String category;

    /**
     * Additional notes or description about the transaction.
     */
    private String notes;

    /**
     * Default constructor for creating an empty {@code TransactionCreateDto}.
     */
    public TransactionCreateDto() { }

    /**
     * Constructs a new {@code TransactionCreateDto} with the specified details.
     *
     * @param amount the amount of money involved in the transaction
     * @param date the date when the transaction occurred
     * @param category the category of the transaction
     * @param notes additional notes or description about the transaction
     * @param walletId the ID of the wallet associated with the transaction
     */
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

    /**
     * Gets the amount of money involved in the transaction.
     *
     * @return the amount of money involved in the transaction
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of money involved in the transaction.
     *
     * @param amount the amount of money involved in the transaction
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the ID of the wallet associated with the transaction.
     *
     * @return the ID of the wallet associated with the transaction
     */
    public Long getWalletId() {
        return walletId;
    }

    /**
     * Sets the ID of the wallet associated with the transaction.
     *
     * @param walletId the ID of the wallet associated with the transaction
     */
    public void setWalletId(Long walletId) {
        this.walletId = walletId;
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
     * Gets additional notes or description about the transaction.
     *
     * @return additional notes or description about the transaction
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets additional notes or description about the transaction.
     *
     * @param notes additional notes or description about the transaction
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Returns a string representation of the transaction create DTO for debugging and logging purposes.
     *
     * @return a string representation of the transaction create DTO
     */
    @Override
    public String toString() {
        return "TransactionCreateDto{" +
                "amount=" + amount +
                ", walletId=" + walletId +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
