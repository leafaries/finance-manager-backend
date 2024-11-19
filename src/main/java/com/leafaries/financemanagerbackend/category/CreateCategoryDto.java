package com.leafaries.financemanagerbackend.category;

/**
 * Data Transfer Object (DTO) class for creating a new category.
 * <p>
 * This class is used to encapsulate the data required to create a new category.
 * </p>
 */
public class CreateCategoryDto {

    /**
     * The name of the category to be created.
     */
    private String name;

    /**
     * Gets the name of the category to be created.
     *
     * @return the name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category to be created.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
