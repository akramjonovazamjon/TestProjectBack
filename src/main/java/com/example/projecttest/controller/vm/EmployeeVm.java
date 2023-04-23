package com.example.projecttest.controller.vm;

import java.math.BigDecimal;

public record EmployeeVm(Long id, String fullName, String phoneNumber, BigDecimal salary, String organization) {
}
