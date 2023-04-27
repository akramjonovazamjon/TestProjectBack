package com.example.projecttest.exception.position;

import lombok.Getter;

@Getter
public class PositionExistByNameException extends RuntimeException {
    private final String name;

    public PositionExistByNameException(String name) {
        super("error.duplicate.position.by_name");
        this.name = name;
    }
}
