package com.example.projecttest.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank(message = "error.invalid.username.not_blank") String username,
        @NotBlank(message = "error.invalid.password.not_blank") String password) {
}
