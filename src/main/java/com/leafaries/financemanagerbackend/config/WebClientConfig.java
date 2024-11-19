package com.leafaries.financemanagerbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for setting up the WebClient.
 * <p>
 * This class provides the configuration for a WebClient.Builder bean.
 * </p>
 */
@Configuration
public class WebClientConfig {

    /**
     * Creates and configures a {@link WebClient.Builder} bean.
     * <p>
     * The WebClient.Builder can be used to build WebClient instances with
     * customized settings.
     * </p>
     *
     * @return the configured {@link WebClient.Builder}
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
