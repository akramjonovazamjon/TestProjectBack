package com.example.projecttest.exception.employee;

import lombok.Getter;

@Getter
public class EmployeeExistByPhoneNumberException extends RuntimeException {
    private final String phoneNumber;

    public EmployeeExistByPhoneNumberException(String phoneNumber) {
        super("error.duplicate.employee.by_phone_number");
        this.phoneNumber = phoneNumber;
    }
}
