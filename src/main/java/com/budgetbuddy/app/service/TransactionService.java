package com.budgetbuddy.app.service;

import com.budgetbuddy.app.dto.TransactionRequestDTO;
import com.budgetbuddy.app.entity.BudgetUser;
import com.budgetbuddy.app.entity.Category;
import com.budgetbuddy.app.entity.Transaction;
import com.budgetbuddy.app.exception.CategoryNotFoundException;
import com.budgetbuddy.app.exception.TransactionNotFoundException;
import com.budgetbuddy.app.exception.UserNotFoundException;
import com.budgetbuddy.app.repository.BudgetUserRepository;
import com.budgetbuddy.app.repository.CategoryRepository;
import com.budgetbuddy.app.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BudgetUserRepository budgetUserRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              BudgetUserRepository budgetUserRepository,
                              CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.budgetUserRepository = budgetUserRepository;
        this.categoryRepository = categoryRepository;
    }

    // VALIDACIONES
    private BudgetUser validateUser(Long userId) {
        return budgetUserRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario con ID " + userId + " no encontrado"));
    }

    private Category validateCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Categoría con ID " + categoryId + " no encontrada"));
    }

    private Transaction validateTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transacción con ID " + transactionId + " no encontrada"));
    }

    // CREATE
    public Transaction createTransaction(TransactionRequestDTO request) {
        BudgetUser user = validateUser(request.getUserId());
        Category category = validateCategory(request.getCategoryId());

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
    public Transaction getTransactionById(Long id) {
        return validateTransaction(id);
    }

    // UPDATE
    public Transaction updateTransaction(Long id, TransactionRequestDTO request) {
        Transaction existingTransaction = validateTransaction(id);
        BudgetUser user = validateUser(request.getUserId());
        Category category = validateCategory(request.getCategoryId());

        existingTransaction.setAmount(request.getAmount());
        existingTransaction.setDescription(request.getDescription());
        existingTransaction.setDate(request.getDate());
        existingTransaction.setType(request.getType());
        existingTransaction.setUser(user);
        existingTransaction.setCategory(category);

        return transactionRepository.save(existingTransaction);
    }

    // DELETE
    public void deleteTransaction(Long id) {
        Transaction transaction = validateTransaction(id);
        transactionRepository.delete(transaction);
    }
}
