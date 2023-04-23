package com.example.projecttest.mapper;

import com.example.projecttest.controller.vm.EmployeeVm;
import com.example.projecttest.dto.employee.CreateEmployee;
import com.example.projecttest.entity.Employee;
import com.example.projecttest.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organization", source = "organization")
    @Mapping(target = "salary", expression = "java(dto.salary().longValue() < 0 ? BigDecimal.ZERO : dto.salary())")
    Employee newEmployee(CreateEmployee dto, Organization organization);

    @Mapping(target = "organization", source = "employee.organization.name")
    EmployeeVm asEmployeeVm(Employee employee);

    List<EmployeeVm> asEmployeeList(List<Employee> employees);

}
