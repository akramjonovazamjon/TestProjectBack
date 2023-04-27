package com.example.projecttest.exception.employee;

public class EmployeeItemNotFoundException extends RuntimeException {
    public EmployeeItemNotFoundException() {
        super("error.not_found.employee_item.by_id");
    }
}
