package com.example.projecttest.exception.user;

public class UserPasswordNoMatchesException extends RuntimeException {
    public UserPasswordNoMatchesException() {
        super("error.password.not_matches");
    }
}
