package com.budgetbuddy.app.controller;

import com.budgetbuddy.app.dto.TransactionRequestDTO;
import com.budgetbuddy.app.entity.Transaction;
import com.budgetbuddy.app.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid  @RequestBody TransactionRequestDTO request) {
        Transaction transaction = transactionService.createTransaction(request);
        if (transaction == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(transaction);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id,
                                                         @Valid @RequestBody TransactionRequestDTO request) {
        Transaction updatedTransaction = transactionService.updateTransaction(id, request);
        if (updatedTransaction == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedTransaction);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        boolean deleted = transactionService.deleteTransaction(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}