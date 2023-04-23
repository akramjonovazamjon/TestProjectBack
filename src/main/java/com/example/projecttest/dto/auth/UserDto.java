package com.example.projecttest.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank(message = "error.invalid.name.not_blank") String name,
        @NotBlank(message = "error.invalid.username.not_blank") String username,
        @NotBlank(message = "error.invalid.password.not_blank") String password) {
}
