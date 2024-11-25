package com.leafaries.financemanagerbackend.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for cache invalidation operations.
 * <p>
 * This class provides methods to clear specific caches used within the application.
 * </p>
 */
@Service
@Slf4j
public class CacheInvalidationService {
    /**
     * Clears all entries in the "exchangeRates" cache.
     * <p>
     * This method uses the {@link CacheEvict} annotation to remove all entries
     * from the specified cache to ensure that the data is refreshed.
     * </p>
     */
    @CacheEvict(value = "exchangeRates", allEntries = true)
    public void clearcache() {
        log.info("Clearing all entries in the exchangeRates cache.");
    }
}
