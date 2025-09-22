package com.budgetbuddy.app.controller;


import com.budgetbuddy.app.dto.TransactionRequestDTO;
import com.budgetbuddy.app.entity.BudgetUser;
import com.budgetbuddy.app.entity.Category;
import com.budgetbuddy.app.entity.Transaction;
import com.budgetbuddy.app.repository.BudgetUserRepository;
import com.budgetbuddy.app.repository.CategoryRepository;
import com.budgetbuddy.app.repository.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionRepository transactionRepository;
    private final BudgetUserRepository budgetUserRepository;
    private final CategoryRepository categoryRepository;

    public TransactionController(TransactionRepository transactionRepository, BudgetUserRepository budgetUserRepository, CategoryRepository categoryRepository){
        this.transactionRepository = transactionRepository;
        this.budgetUserRepository = budgetUserRepository;
        this.categoryRepository = categoryRepository;
    }

    private BudgetUser validateUser(Long userid){
        return budgetUserRepository.findById(userid).orElse(null);
    }

    private Category validateCategory(Long categoryId){
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequestDTO request) {
        BudgetUser user = validateUser(request.getUserId());
        if (user == null) return ResponseEntity.notFound().build();

        Category category = validateCategory(request.getCategoryId());
        if (category == null) return ResponseEntity.notFound().build();

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setDate(request.getDate());
        transaction.setType(request.getType());
        transaction.setUser(user);
        transaction.setCategory(category);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return transactionRepository.findById(id)
                .map(transaction -> ResponseEntity.ok().body(transaction))
                .orElse(ResponseEntity.notFound().build());
    }

}
