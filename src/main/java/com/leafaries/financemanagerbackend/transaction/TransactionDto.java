package com.leafaries.financemanagerbackend.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for transferring transaction information.
 * Contains the necessary fields that are returned or received from the client.
 */
public class TransactionDto {

    /**
     * The ID of the transaction.
     */
    private Long id;

    /**
     * The ID of the wallet associated with the transaction.
     */
    private Long walletId;

    /**
     * The amount of money involved in the transaction.
     */
    private BigDecimal amount;

    /**
     * The date when the transaction occurred.
     */
    private LocalDate date;

    /**
     * The category of the transaction (e.g., groceries, salary).
     */
    private String category;

    /**
     * Additional notes or description about the transaction.
     */
    private String notes;

    /**
     * Default constructor for creating an empty {@code TransactionDto}.
     */
    public TransactionDto() { }

    /**
     * Constructs a new {@code TransactionDto} with the specified details.
     *
     * @param id the ID of the transaction
     * @param walletId the ID of the wallet associated with the transaction
     * @param amount the amount of money involved in the transaction
     * @param date the date when the transaction occurred
     * @param category the category of the transaction
     * @param notes additional notes or description about the transaction
     */
    public TransactionDto(Long id, Long walletId, BigDecimal amount, LocalDate date, String category, String notes) {
        this.id = id;
        this.walletId = walletId;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.notes = notes;
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
     * Returns a string representation of the transaction DTO for debugging and logging purposes.
     *
     * @return a string representation of the transaction DTO
     */
    @Override
    public String toString() {
        return "TransactionDto{" +
                "id=" + id +
                ", walletId=" + walletId +
                ", amount=" + amount +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
