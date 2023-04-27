package com.example.projecttest.service;

import com.example.projecttest.dto.employee.CreateEmployee;
import com.example.projecttest.dto.employee.UpdateEmployee;
import com.example.projecttest.entity.Employee;
import com.example.projecttest.entity.Position;
import com.example.projecttest.exception.employee.EmployeeExistByPhoneNumberException;
import com.example.projecttest.exception.employee.EmployeeNotFoundByIdException;
import com.example.projecttest.mapper.EmployeeMapper;
import com.example.projecttest.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    private final PositionService positionService;
    private final EmployeeMapper mapper;

    public Employee create(CreateEmployee dto) {
        if (repository.existsByPhoneNumber(dto.phoneNumber()))
            throw new EmployeeExistByPhoneNumberException(dto.phoneNumber());

        Position position = positionService.getById(dto.positionId());

        Employee employee = mapper.newEmployee(dto, position);

        return repository.save(employee);
    }

    public Employee getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundByIdException(id));
    }

    public List<Employee> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public void update(Long id, UpdateEmployee dto) {
        boolean exists = repository.existsByPhoneNumberAndIdNot(dto.phoneNumber(), id);
        if (exists)
            throw new EmployeeExistByPhoneNumberException(dto.phoneNumber());

        Position position = positionService.getById(dto.positionId());
        Employee employee = getById(id);
        mapper.asUpdateEmployee(dto, position, employee);
        repository.save(employee);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
