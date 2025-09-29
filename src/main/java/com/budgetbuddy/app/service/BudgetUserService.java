package com.budgetbuddy.app.service;

import com.budgetbuddy.app.entity.BudgetUser;
import com.budgetbuddy.app.repository.BudgetUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetUserService {

    private final BudgetUserRepository budgetUserRepository;

    public BudgetUserService(BudgetUserRepository budgetUserRepository) {
        this.budgetUserRepository = budgetUserRepository;
    }

    // CREATE
    public BudgetUser createUser(BudgetUser user) {
        return budgetUserRepository.save(user);
    }

    // READ ALL
    public List<BudgetUser> getAllUsers() {
        return budgetUserRepository.findAll();
    }

    // READ ONE
    public Optional<BudgetUser> getUserById(Long id) {
        return budgetUserRepository.findById(id);
    }

    // UPDATE
    public BudgetUser updateUser(Long id, BudgetUser updatedUser) {
        return budgetUserRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    return budgetUserRepository.save(user);
                })
                .orElse(null);
    }

    // DELETE
    public boolean deleteUser(Long id) {
        return budgetUserRepository.findById(id)
                .map(user -> {
                    budgetUserRepository.delete(user);
                    return true;
                })
                .orElse(false);
    }
}
