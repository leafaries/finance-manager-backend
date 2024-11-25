package com.leafaries.financemanagerbackend.category;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class for transferring category data.
 * <p>
 * This class is used to encapsulate the data related to a category
 * for transferring between different layers of the application.
 * </p>
 */
@Getter
@Setter
public class CategoryDto {
    /**
     * The unique identifier of the category.
     */
    private Long id;

    /**
     * The name of the category.
     */
    private String name;
}
