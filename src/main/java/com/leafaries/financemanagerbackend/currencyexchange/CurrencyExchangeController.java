package com.leafaries.financemanagerbackend.currencyexchange;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

/**
 * REST controller for handling currency exchange operations.
 * <p>
 *     This controller provides an endpoint to retrieve the latest exchange rates.
 * </p>
 */
@RestController
@RequestMapping("/currency-exchange")
@AllArgsConstructor
@Slf4j
public class CurrencyExchangeController {
    private final CurrencyExchangeService currencyExchangeService;

    /**
     * Retrieves the latest exchange rates.
     * <p>
     *     This endpoint returns the most recent currency exchange rates wrapped in a {@link Mono}.
     * </p>
     *
     * @return a {@link Mono} containing the latest {@link CurrencyExchangeResponseDto} with exchange rates data
     */
    @GetMapping("/latest-exchange-rates")
    public Mono<CurrencyExchangeResponseDto> getLatestExchangeRates() {
        log.info("Received request to fetch exchange rates");
        return currencyExchangeService.getLatestExchangeRates()
                .doOnSuccess(response -> log.info("Returning fetched exchange rates to client: {}", response))
                .doOnError(throwable -> log.error("Failed to return exchange rates to client", throwable));
    }

    @GetMapping("/historical/{date}")
    public Mono<CurrencyExchangeResponseDto> getHistoricalExchangeRates(@PathVariable LocalDate date) {
        log.info("Received request to fetch historical exchange rates on date: {}", date);
        return currencyExchangeService.getHistoricalExchangeRates(date)
                .doOnSuccess(response -> log.info("Returning fetched historical exchange rates to client: {}", response))
                .doOnError(throwable -> log.error("Failed to return historical exchange rates to client:", throwable));
    }
}
