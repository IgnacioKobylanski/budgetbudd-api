package com.budgetbuddy.app.service;

import com.budgetbuddy.app.entity.Category;
import com.budgetbuddy.app.exception.CategoryNotFoundException;
import com.budgetbuddy.app.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Categoría con ID " + id + " no encontrada"));
    }

    // UPDATE
    public Category updateCategory(Long id, Category updateCategory) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("No se puede actualizar. Categoría con ID " + id + " no encontrada"));

        existingCategory.setName(updateCategory.getName());
        return categoryRepository.save(existingCategory);
    }

    // DELETE
    public void deleteCategory(Long id) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("No se puede eliminar. Categoría con ID " + id + " no encontrada"));

        categoryRepository.delete(existingCategory);
    }
}
