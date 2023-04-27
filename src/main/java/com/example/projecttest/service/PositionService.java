package com.example.projecttest.service;

import com.example.projecttest.dto.position.CreatePosition;
import com.example.projecttest.entity.Organization;
import com.example.projecttest.entity.Position;
import com.example.projecttest.exception.position.PositionExistByNameException;
import com.example.projecttest.exception.position.PositionNotFoundByIdException;
import com.example.projecttest.exception.position.PositionNotFoundByNameException;
import com.example.projecttest.mapper.PositionMapper;
import com.example.projecttest.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository repository;
    private final PositionMapper mapper;
    private final OrganizationService organizationService;

    public Position create(CreatePosition dto) {
        boolean existsByName = repository.existsByName(dto.name());
        if (existsByName)
            throw new PositionExistByNameException(dto.name());
        Organization organization = organizationService.getById(dto.orgId());
        Position position = mapper.newPosition(dto, organization);
        return repository.save(position);
    }

    public Position getByName(String name) {
        return repository.findByName(name).orElseThrow(() -> new PositionNotFoundByNameException(name));
    }

    public Position getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new PositionNotFoundByIdException(id));
    }

    public List<Position> getAll() {
        return repository.findAll();
    }
}
