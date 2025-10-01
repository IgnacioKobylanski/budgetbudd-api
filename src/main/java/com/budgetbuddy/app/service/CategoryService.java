package com.budgetbuddy.app.service;

import com.budgetbuddy.app.entity.Category;
import com.budgetbuddy.app.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // CREATE
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // READ ALL
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // READ ONE
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // UPDATE
    public Category updateCategory(Long id, Category updateCategory) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(updateCategory.getName());
                    return categoryRepository.save(category);
                })
                .orElse(null);
    }

    // DELETE
    public boolean deleteCategory(Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return true;
                })
                .orElse(false);
    }
}
