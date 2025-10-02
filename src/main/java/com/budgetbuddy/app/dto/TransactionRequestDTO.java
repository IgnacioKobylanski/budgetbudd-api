package com.budgetbuddy.app.dto;


import com.budgetbuddy.app.entity.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDTO {
    @NotNull
    @Positive(message = "El monto debe ser mayor a 0")
    private Double amount;

    @NotBlank(message = "La descripcion no puede estar vacia")
    @Size(max = 255, message = "La descripción no puede superar 255 caracteres")
    private String description;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate date;

    @NotNull(message = "El tipo de transaccion es obligatorio")
    private TransactionType type;

    @NotNull(message = "El usuario es obligatorio")
    private Long userId;

    @NotNull(message = "La categoría es obligatoria")
    private Long categoryId;
}
