package com.example.projecttest.mapper;

import com.example.projecttest.controller.vm.EmployeeVm;
import com.example.projecttest.dto.employee.CreateEmployee;
import com.example.projecttest.dto.employee.UpdateEmployee;
import com.example.projecttest.entity.Employee;
import com.example.projecttest.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "position", source = "position")
    Employee newEmployee(CreateEmployee dto, Position position);

    @Mapping(target = "position", source = "employee.position.name")
    @Mapping(target = "salary", source = "employee.position.salary")
    @Mapping(target = "organization", source = "employee.position.organization.name")
    EmployeeVm asEmployeeVm(Employee employee);

    List<EmployeeVm> asEmployeeList(List<Employee> employees);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fullName", source = "dto.fullName")
    @Mapping(target = "phoneNumber", source = "dto.phoneNumber")
    @Mapping(target = "position", source = "position")
    void asUpdateEmployee(UpdateEmployee dto, Position position, @MappingTarget Employee employee);

}
