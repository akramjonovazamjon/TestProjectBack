package com.example.projecttest.controller.vm;


import java.math.BigDecimal;
import java.time.YearMonth;

public record EmployeeWorkingSalary(Long employeeId,
                                    String fullName,
                                    BigDecimal salaryForHour,
                                    String phoneNumber,
                                    String organization,
                                    YearMonth month,
                                    Long monthlySalary,
                                    Long monthlyWorkingHour) {
}
