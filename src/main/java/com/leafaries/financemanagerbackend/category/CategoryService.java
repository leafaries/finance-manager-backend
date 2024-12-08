package com.leafaries.financemanagerbackend.category;

import com.leafaries.financemanagerbackend.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing categories.
 * Provides methods for creating, retrieving, updating, and deleting categories.
 */
@Slf4j
@AllArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    /**
     * Creates a new category.
     *
     * @param createCategoryDto the DTO containing the category data to be created
     * @return the created category as a {@link CategoryDto}
     */
    public CategoryDto createCategory(CreateCategoryDto createCategoryDto) {
        log.debug("Creating category with data: {}", createCategoryDto);
        Category category = modelMapper.map(createCategoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        log.debug("Saved category entity: {}", savedCategory);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    /**
     * Retrieves all categories.
     *
     * @return a list of all categories as {@link CategoryDto} objects
     */
    public List<CategoryDto> getAllCategories() {
        log.debug("Fetching all categories");
        List<Category> categories = categoryRepository.findAll();
        log.debug("Fetched all categories entities: {}", categories);
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
        log.debug("Fetching category with id: {}", id);
        Optional<Category> category = categoryRepository.findById(id);
        log.debug("Fetched category entity: {}", category);
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
        log.debug("Updating category with id: {} and data: {}", id, createCategoryDto);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Category mappedCategory = modelMapper.map(createCategoryDto, Category.class);
        category.setName(mappedCategory.getName());
        Category updatedCategory = categoryRepository.save(category);
        CategoryDto updatedCategoryDto = modelMapper.map(updatedCategory, CategoryDto.class);
        log.debug("Updated category entity: {}", updatedCategory);
        return Optional.ofNullable(updatedCategoryDto);
    }

    /**
     * Deletes a category by its unique identifier.
     *
     * @param id the unique identifier of the category to be deleted
     */
    public void deleteCategory(Long id) {
        log.debug("Deleting category with id: {}", id);
        categoryRepository.findById(id).ifPresent(categoryRepository::delete);
        log.debug("Deleting category with id: {}", id);
    }

}
