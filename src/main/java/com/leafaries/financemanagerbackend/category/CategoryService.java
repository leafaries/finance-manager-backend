package com.leafaries.financemanagerbackend.category;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing categories.
 * Provides methods for creating, retrieving, updating, and deleting categories.
 */
@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new {@code CategoryService} with the specified category repository and model mapper.
     *
     * @param categoryRepository the repository for managing category data
     * @param modelMapper the mapper for converting between entities and DTOs
     */
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Creates a new category.
     *
     * @param createCategoryDto the DTO containing the category data to be created
     * @return the created category as a {@link CategoryDto}
     */
    public CategoryDto createCategory(CreateCategoryDto createCategoryDto) {
        logger.debug("Creating category with data: {}", createCategoryDto);
        Category category = modelMapper.map(createCategoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        logger.debug("Saved category entity: {}", savedCategory);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    /**
     * Retrieves all categories.
     *
     * @return a list of all categories as {@link CategoryDto} objects
     */
    public List<CategoryDto> getAllCategories() {
        logger.debug("Fetching all categories");
        List<Category> categories = categoryRepository.findAll();
        logger.debug("Fetched all categories entities: {}", categories);
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .toList();
        return categoryDtos;
    }

    /**
     * Retrieves a category by its unique identifier.
     *
     * @param id the unique identifier of the category
     * @return an {@link Optional} containing the found category as a {@link CategoryDto}, or {@link Optional#empty()} if not found
     */
    public Optional<CategoryDto> getCategoryById(Long id) {
        logger.debug("Fetching category with id: {}", id);
        Optional<Category> category = categoryRepository.findById(id);
        logger.debug("Fetched category entity: {}", category);
        return category.map(cat -> {
            CategoryDto categoryDto = modelMapper.map(cat, CategoryDto.class);
            return categoryDto;
        });
    }

    /**
     * Updates an existing category.
     *
     * @param id the unique identifier of the category to be updated
     * @param createCategoryDto the DTO containing the updated category data
     * @return an {@link Optional} containing the updated category as a {@link CategoryDto}, or {@link Optional#empty()} if not found
     */
    public Optional<CategoryDto> updateCategory(Long id, CreateCategoryDto createCategoryDto) {
        logger.debug("Updating category with id: {} and data: {}", id, createCategoryDto);
        return categoryRepository.findById(id).map(category -> {
            modelMapper.map(createCategoryDto, category);
            Category updatedCategory = categoryRepository.save(category);
            logger.debug("Updated category entity: {}", updatedCategory);
            CategoryDto updatedCategoryDto = modelMapper.map(updatedCategory, CategoryDto.class);
            return updatedCategoryDto;
        });
    }

    /**
     * Deletes a category by its unique identifier.
     *
     * @param id the unique identifier of the category to be deleted
     */
    public void deleteCategory(Long id) {
        logger.debug("Deleting category with id: {}", id);
        categoryRepository.findById(id).ifPresent(categoryRepository::delete);
        logger.debug("Deleting category with id: {}", id);
    }
}
