package com.leafaries.financemanagerbackend.category;

import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) class for creating a new category.
 * <p>
 * This class is used to encapsulate the data required to create a new category.
 * </p>
 */
@Getter
@Setter
public class CreateCategoryDto {
    /**
     * The name of the category to be created.
     */
    private String name;
}
