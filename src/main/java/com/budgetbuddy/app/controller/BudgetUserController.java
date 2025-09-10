package com.budgetbuddy.app.controller;


import com.budgetbuddy.app.entity.BudgetUser;
import com.budgetbuddy.app.repository.BudgetUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class BudgetUserController {


    private final BudgetUserRepository budgetUserRepository;

    public BudgetUserController(BudgetUserRepository budgetUserRepository) {
        this.budgetUserRepository = budgetUserRepository;
    }

    @GetMapping
    public List<BudgetUser> getAllUsers(){
        return budgetUserRepository.findAll();
    }

    @PostMapping
    public BudgetUser createUser(@RequestBody BudgetUser user){
        return budgetUserRepository.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetUser> getUserById(@PathVariable Long id){
        return budgetUserRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetUser> updateUser(@PathVariable Long id, @RequestBody BudgetUser updatedUser){
        return budgetUserRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    BudgetUser savedUser = budgetUserRepository.save(user);
                    return ResponseEntity.ok().body(savedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        return budgetUserRepository.findById(id)
                .map(user -> {
                    budgetUserRepository.delete(user);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
