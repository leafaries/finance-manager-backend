package com.leafaries.financemanagerbackend.currencyexchange;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class CurrencyExchangeServiceTest {
/*
    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private CurrencyExchangeService currencyExchangeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClientBuilder.baseUrl(any(String.class))).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
//        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
//        when(requestHeadersUriSpec.uri(any(WebClient.UriBuilder.class))).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
    }

    @Test
    public void testGetLatestExchangeRates_Success() {
        // Mock the response
        CurrencyExchangeResponseDto mockResponse = new CurrencyExchangeResponseDto();
        when(responseSpec.bodyToMono(CurrencyExchangeResponseDto.class)).thenReturn(Mono.just(mockResponse));

        Mono<CurrencyExchangeResponseDto> result = currencyExchangeService.getLatestExchangeRates();

        // Verify the result
        StepVerifier.create(result)
                .expectNext(mockResponse)
                .verifyComplete();
    }

    @Test
    public void testGetLatestExchangeRates_Error() {
        // Mock the response
        when(responseSpec.bodyToMono(CurrencyExchangeResponseDto.class)).thenReturn(Mono.error(new WebClientResponseException(500, "Internal Server Error", null, null, null)));

        Mono<CurrencyExchangeResponseDto> result = currencyExchangeService.getLatestExchangeRates();

        // Verify the result
        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();
    }

 */
}
