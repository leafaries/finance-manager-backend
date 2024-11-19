package com.leafaries.financemanagerbackend.category;

/**
 * Data Transfer Object (DTO) class for transferring category data.
 * <p>
 * This class is used to encapsulate the data related to a category
 * for transferring between different layers of the application.
 * </p>
 */
public class CategoryDto {

    /**
     * The unique identifier of the category.
     */
    private Long id;

    /**
     * The name of the category.
     */
    private String name;

    // Getters and setters

    /**
     * Gets the unique identifier of the category.
     *
     * @return the unique identifier of the category
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the category.
     *
     * @param id the unique identifier to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the category.
     *
     * @return the name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
