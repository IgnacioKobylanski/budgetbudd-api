package com.budgetbuddy.app.controller;

import com.budgetbuddy.app.entity.BudgetUser;
import com.budgetbuddy.app.service.BudgetUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class BudgetUserController {

    private final BudgetUserService budgetUserService;

    public BudgetUserController(BudgetUserService budgetUserService) {
        this.budgetUserService = budgetUserService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<BudgetUser> createUser(@RequestBody BudgetUser user) {
        return ResponseEntity.ok(budgetUserService.createUser(user));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<BudgetUser>> getAllUsers() {
        return ResponseEntity.ok(budgetUserService.getAllUsers());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<BudgetUser> getUserById(@PathVariable Long id) {
        return budgetUserService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<BudgetUser> updateUser(@PathVariable Long id, @RequestBody BudgetUser updatedUser) {
        BudgetUser user = budgetUserService.updateUser(id, updatedUser);
        if (user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = budgetUserService.deleteUser(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
