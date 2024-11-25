package com.leafaries.financemanagerbackend.currencyexchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Data Transfer Object (DTO) for representing the response of a currency exchange rate request.
 * <p>
 * This DTO includes various fields such as disclaimer, license, timestamp, base currency,
 * and a map of currency exchange rates.
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CurrencyExchangeResponseDto {
    /**
     * Disclaimer associated with the exchange rates data.
     */
    private String disclaimer;

    /**
     * License information for using the exchange rates data.
     */
    private String license;

    /**
     * Timestamp when the exchange rates were last updated.
     * Represented in seconds since the epoch.
     */
    private long timestamp;

    /**
     * Base currency for the exchange rates.
     */
    private String base;

    /**
     * Map of currency exchange rates.
     * The keys are currency codes and the values are the exchange rates.
     */
    private Map<String, Double> rates;
}
