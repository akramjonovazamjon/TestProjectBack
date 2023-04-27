package com.example.projecttest.exception.position;

import lombok.Getter;

@Getter
public class PositionNotFoundByIdException extends RuntimeException {
    private final Long id;

    public PositionNotFoundByIdException(Long id) {
        super("error.not_found.position.by_id");
        this.id = id;
    }
}
