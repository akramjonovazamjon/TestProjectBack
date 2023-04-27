package com.example.projecttest.exception.position;

import lombok.Getter;

@Getter
public class PositionNotFoundByNameException extends RuntimeException {
    private final String name;

    public PositionNotFoundByNameException(String name) {
        super("error.not_found.position.by_name");
        this.name = name;
    }
}
