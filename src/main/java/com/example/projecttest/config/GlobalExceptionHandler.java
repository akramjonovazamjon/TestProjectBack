package com.example.projecttest.config;

import com.example.projecttest.dto.ResponseData;
import com.example.projecttest.exception.employee.EmployeeExistByPhoneNumberException;
import com.example.projecttest.exception.employee.EmployeeNotFoundByIdException;
import com.example.projecttest.exception.organization.OrganizationExistByNameException;
import com.example.projecttest.exception.organization.OrganizationNotFoundByIdException;
import com.example.projecttest.exception.position.PositionExistByNameException;
import com.example.projecttest.exception.position.PositionNotFoundByIdException;
import com.example.projecttest.exception.position.PositionNotFoundByNameException;
import com.example.projecttest.exception.user.UserExistByUsernameException;
import com.example.projecttest.exception.user.UserNotFoundByUsernameException;
import com.example.projecttest.exception.user.UserPasswordNoMatchesException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmployeeExistByPhoneNumberException.class)
    public ResponseData<Object> handleEmployeeExistByPhoneNumberException(EmployeeExistByPhoneNumberException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EmployeeNotFoundByIdException.class)
    public ResponseData<Object> handleEmployeeNotFoundByIdException(EmployeeNotFoundByIdException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(OrganizationExistByNameException.class)
    public ResponseData<Object> handleOrganizationExistByNameException(OrganizationExistByNameException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(OrganizationNotFoundByIdException.class)
    public ResponseData<Object> handleOrganizationNotFoundByIdException(OrganizationNotFoundByIdException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserExistByUsernameException.class)
    public ResponseData<Object> handleUserExistByUsernameException(UserExistByUsernameException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundByUsernameException.class)
    public ResponseData<Object> handleUserNotFoundByUsernameException(UserNotFoundByUsernameException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserPasswordNoMatchesException.class)
    public ResponseData<Object> handleUserPasswordNoMatchesException(UserPasswordNoMatchesException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PositionExistByNameException.class)
    public ResponseData<Object> handlePositionExistByNameException(PositionExistByNameException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PositionNotFoundByNameException.class)
    public ResponseData<Object> handlePositionNotFoundByNameException(PositionNotFoundByNameException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(PositionNotFoundByIdException.class)
    public ResponseData<Object> handlePositionNotFoundByIdException(PositionNotFoundByIdException e) {
        return ResponseData.errorOf(e.getMessage());
    }

}
