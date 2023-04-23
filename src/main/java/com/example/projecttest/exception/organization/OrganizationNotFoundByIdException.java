package com.example.projecttest.exception.organization;

import lombok.Getter;

@Getter
public class OrganizationNotFoundByIdException extends RuntimeException{
    private final Long id;

    public OrganizationNotFoundByIdException(Long id) {
        super("error.not_found.organization.by_id");
        this.id = id;
    }
}
