package com.example.projecttest.controller.vm;

import java.time.LocalDateTime;

public record EmployeeItemVm(Long id, LocalDateTime arrivalDate, LocalDateTime exitDate, Long workingHour,
                             EmployeeVm employee) {
}
