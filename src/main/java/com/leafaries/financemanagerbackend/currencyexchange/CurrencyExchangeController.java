package com.leafaries.financemanagerbackend.currencyexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * REST controller for handling currency exchange operations.
 * <p>
 *     This controller provides an endpoint to retrieve the latest exchange rates.
 * </p>
 */
@RestController
@RequestMapping("/api/currency-exchange")
public class CurrencyExchangeController {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    private final CurrencyExchangeService currencyExchangeService;

    /**
     * Constructs a new {@code CurrencyExchangeController} with the specified currency exchange service.
     *
     * @param currencyExchangeService the service for managing currency exchange data
     */
    public CurrencyExchangeController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    /**
     * Retrieves the latest exchange rates.
     * <p>
     *     This endpoint returns the most recent currency exchange rates wrapped in a {@link Mono}.
     * </p>
     *
     * @return a {@link Mono} containing the latest {@link CurrencyExchangeResponseDto} with exchange rates data
     */
    @GetMapping("latest-exchange-rates")
    public Mono<CurrencyExchangeResponseDto> getLatestExchangeRates() {
        logger.info("Received request to fetch exchange rates");
        return currencyExchangeService.getLatestExchangeRates()
                .doOnSuccess(response -> logger.info("Returning fetched exchange rates to client: {}", response))
                .doOnError(throwable -> logger.error("Failed to return exchange rates to client", throwable));
    }
}
