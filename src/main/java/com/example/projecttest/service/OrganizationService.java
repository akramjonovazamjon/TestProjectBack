package com.example.projecttest.service;

import com.example.projecttest.dto.organization.CreateOrganization;
import com.example.projecttest.entity.Organization;
import com.example.projecttest.exception.organization.OrganizationExistByNameException;
import com.example.projecttest.exception.organization.OrganizationNotFoundByIdException;
import com.example.projecttest.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository repository;

    public Organization create(CreateOrganization dto) {
        if (repository.existsByName(dto.name()))
            throw new OrganizationExistByNameException(dto.name());

        Organization organization = Organization.of(dto);
        return repository.save(organization);
    }

    public Organization getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new OrganizationNotFoundByIdException(id));
    }

    public List<Organization> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
