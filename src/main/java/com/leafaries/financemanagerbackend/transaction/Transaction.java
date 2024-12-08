package com.leafaries.financemanagerbackend.transaction;

import com.leafaries.financemanagerbackend.wallet.Wallet;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity class representing a financial transaction.
 * Maps to database table for transaction storage.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
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

}
