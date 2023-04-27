package com.example.projecttest.dto.position;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreatePosition(
        @NotBlank(message = "error.invalid.name.not_blank") String name,
        @NotNull(message = "error.invalid.salary.not_null") BigDecimal salary,
        @NotNull(message = "error.invalid.organization_id.not_null") Long orgId) {
}
