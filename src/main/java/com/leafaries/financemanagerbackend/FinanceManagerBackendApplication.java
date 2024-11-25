package com.leafaries.financemanagerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main class for the Finance Manager Backend application.
 * <p>
 * This is the entry point of the Finance Manager Backend application. It is responsible for
 * bootstrapping and launching the Spring application context.
 * </p>
 * <p>
 * The application is configured using Spring Boot, and caching is enabled using the {@code @EnableCaching} annotation.
 * </p>
 */
@SpringBootApplication
@EnableCaching
public class FinanceManagerBackendApplication {
    /**
     * Main method for starting the Finance Manager Backend application.
     * <p>
     * This method delegates to Spring Boot's {@link SpringApplication#run(Class, String...)} method to
     * launch the application.
     * </p>
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(FinanceManagerBackendApplication.class, args);
    }
}
