package com.example.projecttest.dto.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateEmployee(
        @NotBlank(message = "error.invalid.full_name.not_blank") String fullName,
        @NotBlank(message = "error.invalid.phone_number.not_blank") String phoneNumber,
        @NotNull(message = "error.invalid.salary.not_null") BigDecimal salary) {
}
