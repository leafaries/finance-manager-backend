package com.leafaries.financemanagerbackend.category;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        log.debug("Received request to create category with data: {}", createCategoryDto);
        CategoryDto createdCategory = categoryService.createCategory(createCategoryDto);
        log.debug("Created category: {}", createdCategory);
//        return ResponseEntity.ok(createdCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        log.debug("Received request to fetch all categories");
        List<CategoryDto> categories = categoryService.getAllCategories();
        log.debug("Fetched all categories: {}", categories);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        log.debug("Received request to get category with id: {}", id);
        Optional<CategoryDto> categoryDto = categoryService.getCategoryById(id);
        return categoryDto.map(dto -> {
            log.debug("Fetched category: {}", dto);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> {
            log.warn("Category with id: {} not found", id);
            return ResponseEntity.notFound().build();
        });
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CreateCategoryDto createCategoryDto) {
        log.debug("Received request to update category with id: {} and data: {}", id, createCategoryDto);
        Optional<CategoryDto> updatedCategory = categoryService.updateCategory(id, createCategoryDto);
        return updatedCategory.map(dto -> {
            log.debug("Updated category: {}", dto);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> {
            log.warn("Category with id: {} not found for update", id);
            return ResponseEntity.notFound().build();
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        log.debug("Received request to delete category with id: {}", id);
        categoryService.deleteCategory(id);
        log.debug("Deleted category with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}
