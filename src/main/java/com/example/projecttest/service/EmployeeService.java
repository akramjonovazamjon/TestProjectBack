package com.example.projecttest.service;

import com.example.projecttest.dto.employee.CreateEmployee;
import com.example.projecttest.entity.Employee;
import com.example.projecttest.entity.Organization;
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
    private final OrganizationService organizationService;
    private final EmployeeMapper mapper;

    public Employee create(Long orgId, CreateEmployee dto) {
        if (repository.existsByPhoneNumber(dto.phoneNumber()))
            throw new EmployeeExistByPhoneNumberException(dto.phoneNumber());

        Organization organization = organizationService.getById(orgId);

        Employee employee = mapper.newEmployee(dto, organization);

        return repository.save(employee);
    }

    public Employee getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundByIdException(id));
    }

    public List<Employee> getOrgEmployees(Long orgId, Pageable pageable) {
        return repository.findAllByOrganizationId(orgId, pageable).getContent();
    }

    public List<Employee> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
