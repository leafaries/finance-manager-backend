package com.leafaries.financemanagerbackend.category;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public CategoryDto createCategory(CreateCategoryDto createCategoryDto) {
        logger.debug("Creating category with data: {}", createCategoryDto);
        Category category = modelMapper.map(createCategoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        logger.debug("Saved category entity: {}", savedCategory);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    public List<CategoryDto> getAllCategories() {
        logger.debug("Fetching all categories");
        List<Category> categories = categoryRepository.findAll();
        logger.debug("Fetched all categories entities: {}", categories);
        List<CategoryDto> categoryDtos = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .toList();
        return categoryDtos;
    }

    public Optional<CategoryDto> getCategoryById(Long id) {
        logger.debug("Fetching category with id: {}", id);
        Optional<Category> category = categoryRepository.findById(id);
        logger.debug("Fetched category entity: {}", category);
        return category.map(cat -> {
            CategoryDto categoryDto = modelMapper.map(cat, CategoryDto.class);
            return categoryDto;
        });
    }

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

    public void deleteCategory(Long id) {
        logger.debug("Deleting category with id: {}", id);
        categoryRepository.findById(id).ifPresent(categoryRepository::delete);
        logger.debug("Deleting category with id: {}", id);
    }
}
