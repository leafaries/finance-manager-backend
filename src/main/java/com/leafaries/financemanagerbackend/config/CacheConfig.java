package com.leafaries.financemanagerbackend.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the caching mechanism.
 * <p>
 * This class enables Spring's annotation-driven cache management capability.
 * </p>
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Creates and configures a {@link ConcurrentMapCacheManager} bean.
     * <p>
     * The ConcurrentMapCacheManager provides an in-memory cache implementation.
     * </p>
            *
            * @return the configured {@link ConcurrentMapCacheManager}
     */
    @Bean
    public ConcurrentMapCacheManager cacheManager() {
        return new ConcurrentMapCacheManager("getHistory");
    }
}
