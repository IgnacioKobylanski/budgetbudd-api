package com.budgetbuddy.app.repository;

import com.budgetbuddy.app.entity.BudgetUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetUserRepository extends JpaRepository<BudgetUser, Long> {
}
