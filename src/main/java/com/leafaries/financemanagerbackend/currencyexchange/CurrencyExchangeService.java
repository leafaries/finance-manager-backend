package com.leafaries.financemanagerbackend.currencyexchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;

/**
 * Service class for handling currency exchange operations.
 * <p>
 *     This service provides methods to fetch the latest exchange rates
 *     and (in the future) historical exchange rates.
 * </p>
 */
@Service
public class CurrencyExchangeService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeService.class);

    // I know this is insecure code but idc in this particular project
    private static final String APP_ID = "638fd36f55174fb6a220042ccd5f0021";
    private static final String BASE_URL = "https://openexchangerates.org/api";
    private static final String LATEST_JSON_PATH = "/latest.json";

    private final WebClient webClient;

    /**
     * Constructs a new {@code CurrencyExchangeService} with the specified WebClient builder.
     *
     * @param webClientBuilder the WebClient builder for creating WebClient instances
     */
    public CurrencyExchangeService(WebClient.Builder webClientBuilder) {
        this.webClient = WebClient.builder().baseUrl(BASE_URL).build();
    }

    /**
     * Fetches the latest exchange rates from the external API.
     * <p>
     *     This method uses the Open Exchange Rates API to retrieve the latest exchange rates data.
     *     For more information, see the
     *     <a href="https://docs.openexchangerates.org/reference/latest-json">API documentation</a>
     * </p>
     *
     * @return a {@link Mono} containing the latest {@link CurrencyExchangeResponseDto} with exchange rates data
     */
    public Mono<CurrencyExchangeResponseDto> getLatestExchangeRates() {
        logger.info("Fetching exchange rates from external API.");

        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(LATEST_JSON_PATH)
                        .queryParam("app_id", APP_ID)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> {
                    logger.error("Error fetching exchange rates. Status code: {}", clientResponse.statusCode());
                    return Mono.error(new RuntimeException("Failes to fetch exchange rates"));
                })
                .bodyToMono(CurrencyExchangeResponseDto.class)
                .doOnSuccess(response -> {
                    if (response != null) {
                        logger.info("Successfully fetched exchange rates: {}", response);
                    } else {
                        logger.warn("Received null response from the exchange rate API.");
                    }
                })
                .doOnError(throwable -> {
                    if (throwable instanceof WebClientResponseException exception) {
                        logger.error("Error response from external API: {}", exception.getResponseBodyAsString());
                    } else {
                        logger.error("An error occured while fetching exchange rates", throwable);
                    }
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    // TODO: Implement
    /**
     * Fetches historical exchange rates for a specific date.
     * <p>
     * This method is not yet implemented.
     * </p>
     *
     * @param date the date for which to retrieve historical exchange rates
     */
    public void getHistoricalExchangeRates(LocalDate date) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
