package com.example.projecttest.dto.employee;

import java.time.LocalDateTime;

public record UpdateEmployeeItem(LocalDateTime arrivalDate, LocalDateTime exitDate) {
}
