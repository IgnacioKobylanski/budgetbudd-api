package com.budgetbuddy.app.service;


import com.budgetbuddy.app.dto.TransactionRequestDTO;
import com.budgetbuddy.app.entity.BudgetUser;
import com.budgetbuddy.app.entity.Category;
import com.budgetbuddy.app.entity.Transaction;
import com.budgetbuddy.app.repository.BudgetUserRepository;
import com.budgetbuddy.app.repository.CategoryRepository;
import com.budgetbuddy.app.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final BudgetUserRepository budgetUserRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository,BudgetUserRepository budgetUserRepository, CategoryRepository categoryRepository){
        this.transactionRepository = transactionRepository;
        this.budgetUserRepository = budgetUserRepository;
        this.categoryRepository = categoryRepository;
    }

    private BudgetUser validateUser(Long userId) {
        return budgetUserRepository.findById(userId).orElse(null);
    }

    private Category validateCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    // CREATE
    public Transaction createTransaction(TransactionRequestDTO request) {
        BudgetUser user = validateUser(request.getUserId());
        if (user == null) return null;

        Category category = validateCategory(request.getCategoryId());
        if (category == null) return null;

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setDate(request.getDate());
        transaction.setType(request.getType());
        transaction.setUser(user);
        transaction.setCategory(category);

        return transactionRepository.save(transaction);
    }

    // READ ALL
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // READ ONE
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    // UPDATE
    public Transaction updateTransaction(Long id, TransactionRequestDTO request) {
        Transaction existingTransaction = transactionRepository.findById(id).orElse(null);
        if (existingTransaction == null) return null;

        BudgetUser user = validateUser(request.getUserId());
        if (user == null) return null;

        Category category = validateCategory(request.getCategoryId());
        if (category == null) return null;

        existingTransaction.setAmount(request.getAmount());
        existingTransaction.setDescription(request.getDescription());
        existingTransaction.setDate(request.getDate());
        existingTransaction.setType(request.getType());
        existingTransaction.setUser(user);
        existingTransaction.setCategory(category);

        return transactionRepository.save(existingTransaction);
    }

    // DELETE
    public boolean deleteTransaction(Long id) {
        return transactionRepository.findById(id)
                .map(transaction -> {
                    transactionRepository.delete(transaction);
                    return true;
                })
                .orElse(false);
    }
}
