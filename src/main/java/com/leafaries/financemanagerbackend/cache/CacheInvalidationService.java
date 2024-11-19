package com.leafaries.financemanagerbackend.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for cache invalidation operations.
 * <p>
 * This class provides methods to clear specific caches used within the application.
 * </p>
 */
@Service
public class CacheInvalidationService {

    private static final Logger logger = LoggerFactory.getLogger(CacheInvalidationService.class);

    /**
     * Clears all entries in the "exchangeRates" cache.
     * <p>
     * This method uses the {@link CacheEvict} annotation to remove all entries
     * from the specified cache to ensure that the data is refreshed.
     * </p>
     */
    @CacheEvict(value = "exchangeRates", allEntries = true)
    public void clearcache() {
        logger.info("Clearing all entries in the exchangeRates cache.");
    }
}
