package com.budgetbuddy.app.service;

import com.budgetbuddy.app.entity.BudgetUser;
import com.budgetbuddy.app.exception.UserNotFoundException;
import com.budgetbuddy.app.repository.BudgetUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public BudgetUser getUserById(Long id) {
        return budgetUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario con ID " + id + " no encontrado"));
    }

    // UPDATE
    public BudgetUser updateUser(Long id, BudgetUser updatedUser) {
        BudgetUser existingUser = budgetUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se puede actualizar. Usuario con ID " + id + " no encontrado"));

        existingUser.setName(updatedUser.getName());
        return budgetUserRepository.save(existingUser);
    }

    // DELETE
    public void deleteUser(Long id) {
        BudgetUser existingUser = budgetUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se puede eliminar. Usuario con ID " + id + " no encontrado"));
        budgetUserRepository.delete(existingUser);
    }
}
