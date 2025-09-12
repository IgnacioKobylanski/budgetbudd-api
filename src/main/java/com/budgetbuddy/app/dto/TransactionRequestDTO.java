package com.budgetbuddy.app.dto;


import com.budgetbuddy.app.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {
    private Double amount;
    private String description;
    private LocalDate date;
    private TransactionType type;
    private Long userId;
    private Long categoryId;
}
