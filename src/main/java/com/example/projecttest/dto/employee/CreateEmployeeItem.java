package com.example.projecttest.dto.employee;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateEmployeeItem(
        @NotNull(message = "error.invalid.arrival_date.not_null") LocalDateTime arrivalDate,
        @NotNull(message = "error.invalid.exit_date.not_null") LocalDateTime exitDate) {
}
