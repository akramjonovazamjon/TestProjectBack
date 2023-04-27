package com.example.projecttest.controller.vm;


import java.time.YearMonth;

public record EmployeeWorkingSalary(Long employeeId,
                                    String fullName,
                                    String phoneNumber,
                                    String position,
                                    String organization,
                                    YearMonth month,
                                    Double salary,
                                    Long monthlyWorkingHour) {
}
