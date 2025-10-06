package com.budgetbuddy.app.controller;

import com.budgetbuddy.app.entity.BudgetUser;
import com.budgetbuddy.app.service.BudgetUserService;
import org.springframework.http.HttpStatus;
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
        BudgetUser createdUser = budgetUserService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<BudgetUser>> getAllUsers() {
        return ResponseEntity.ok(budgetUserService.getAllUsers());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<BudgetUser> getUserById(@PathVariable Long id) {
        BudgetUser user = budgetUserService.getUserById(id); // lanza UserNotFoundException si no existe
        return ResponseEntity.ok(user);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<BudgetUser> updateUser(@PathVariable Long id, @RequestBody BudgetUser updatedUser) {
        BudgetUser user = budgetUserService.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        budgetUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
