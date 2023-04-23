package com.example.projecttest.dto;

public record ErrorData(String message) {
    public static ErrorData of(String message) {
        return new ErrorData(message);
    }
}
