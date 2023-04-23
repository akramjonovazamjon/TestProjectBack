package com.example.projecttest.exception.user;

import lombok.Getter;

@Getter
public class UserNotFoundByUsernameException extends RuntimeException{
    private final String username;

    public UserNotFoundByUsernameException(String username) {
        super("error.not_found.user.by_username");
        this.username = username;
    }
}
