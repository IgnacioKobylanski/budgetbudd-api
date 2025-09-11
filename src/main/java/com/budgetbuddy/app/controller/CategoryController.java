package com.budgetbuddy.app.controller;


import com.budgetbuddy.app.entity.BudgetUser;
import com.budgetbuddy.app.entity.Category;
import com.budgetbuddy.app.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return  categoryRepository.findAll();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category){
        return categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updateCategory){
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(updateCategory.getName());
                    Category savedCategory = categoryRepository.save(category);
                    return ResponseEntity.ok().body(savedCategory);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
