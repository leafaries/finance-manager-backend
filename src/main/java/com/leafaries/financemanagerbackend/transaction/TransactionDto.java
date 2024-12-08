package com.leafaries.financemanagerbackend.transaction;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for transferring transaction information.
 * Contains the necessary fields that are returned or received from the client.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

}
