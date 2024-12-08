package com.leafaries.financemanagerbackend.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link Category} entities.
 * <p>
 * It extends {@link JpaRepository} which provides JPA related methods
 * for standard data access operations. Custom query methods can also be added.
 * </p>
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Finds a category by its name.
     *
     * @param name the name of the category to be found
     * @return an {@link Optional} containing the found category, or {@link Optional#empty()} if no category found
     */
    Optional<Category> findByName(String name);

}
