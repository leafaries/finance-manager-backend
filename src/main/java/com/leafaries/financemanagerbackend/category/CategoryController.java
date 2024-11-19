package com.leafaries.financemanagerbackend.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        logger.debug("Received request to create category with data: {}", createCategoryDto);
        CategoryDto createdCategory = categoryService.createCategory(createCategoryDto);
        logger.debug("Created category: {}", createdCategory);
//        return ResponseEntity.ok(createdCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        logger.debug("Received request to fetch all categories");
        List<CategoryDto> categories = categoryService.getAllCategories();
        logger.debug("Fetched all categories: {}", categories);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        logger.debug("Received request to get category with id: {}", id);
        Optional<CategoryDto> categoryDto = categoryService.getCategoryById(id);
        return categoryDto.map(dto -> {
            logger.debug("Fetched category: {}", dto);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> {
            logger.warn("Category with id: {} not found", id);
            return ResponseEntity.notFound().build();
        });
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CreateCategoryDto createCategoryDto) {
        logger.debug("Received request to update category with id: {} and data: {}", id, createCategoryDto);
        Optional<CategoryDto> updatedCategory = categoryService.updateCategory(id, createCategoryDto);
        return updatedCategory.map(dto -> {
            logger.debug("Updated category: {}", dto);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> {
            logger.warn("Category with id: {} not found for update", id);
            return ResponseEntity.notFound().build();
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        logger.debug("Received request to delete category with id: {}", id);
        categoryService.deleteCategory(id);
        logger.debug("Deleted category with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}
