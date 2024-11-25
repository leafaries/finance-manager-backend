package com.leafaries.financemanagerbackend.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) for wallet HTTP responses.
 * Contains the necessary fields returned to the client.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WalletResponseDto {
    /**
     * The ID of the wallet.
     */
    private Long id;

    /**
     * The name of the wallet.
     */
    private String name;

    /**
     * The balance of the wallet.
     */
    private BigDecimal balance;

    /**
     * The currency of the wallet.
     */
    private String currency;
}
