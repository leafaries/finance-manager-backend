package com.leafaries.financemanagerbackend.currencyexchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * Data Transfer Object (DTO) for representing the response of a currency exchange rate request.
 * <p>
 * This DTO includes various fields such as disclaimer, license, timestamp, base currency,
 * and a map of currency exchange rates.
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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

    // Getters and setters

    /**
     * Returns the disclaimer associated with the exchange rates data.
     *
     * @return disclaimer text
     */
    public String getDisclaimer() {
        return disclaimer;
    }

    /**
     * Sets the disclaimer associated with the exchange rates data.
     *
     * @param disclaimer disclaimer text
     */
    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    /**
     * Returns the license information for using the exchange rates data.
     *
     * @return license information
     */
    public String getLicense() {
        return license;
    }

    /**
     * Sets the license information for using the exchange rates data.
     *
     * @param license license information
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * Returns the timestamp when the exchange rates were last updated.
     *
     * @return timestamp in seconds since the epoch
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp when the exchange rates were last updated.
     *
     * @param timestamp timestamp in seconds since the epoch
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the base currency for the exchange rates.
     *
     * @return base currency code
     */
    public String getBase() {
        return base;
    }

    /**
     * Sets the base currency for the exchange rates.
     *
     * @param base base currency code
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * Returns the map of currency exchange rates.
     * <p>
     * The map's keys are currency codes, and the values are the exchange rates.
     * </p>
     *
     * @return map of exchange rates
     */
    public Map<String, Double> getRates() {
        return rates;
    }

    /**
     * Sets the map of currency exchange rates.
     *
     * @param rates map of exchange rates
     */
    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
