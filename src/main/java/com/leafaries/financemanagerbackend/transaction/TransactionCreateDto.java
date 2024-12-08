package com.leafaries.financemanagerbackend.transaction;

import lombok.*;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for creating a new financial transaction.
 * Contains the necessary fields required for transaction creation.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

}
