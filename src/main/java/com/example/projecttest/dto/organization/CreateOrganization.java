package com.example.projecttest.dto.organization;

import jakarta.validation.constraints.NotBlank;

public record CreateOrganization(
        @NotBlank(message = "error.invalid.name.not_blank") String name) {
}
