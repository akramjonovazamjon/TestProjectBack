package com.example.projecttest.exception.employee;

import lombok.Getter;

@Getter
public class EmployeeNotFoundByIdException extends RuntimeException {
    private final Long id;

    public EmployeeNotFoundByIdException(Long id) {
        super("error.not_found.employee.by_id");
        this.id = id;
    }
}
