package com.example.projecttest.mapper;

import com.example.projecttest.controller.vm.EmployeeItemVm;
import com.example.projecttest.dto.employee.CreateEmployeeItem;
import com.example.projecttest.dto.employee.UpdateEmployeeItem;
import com.example.projecttest.entity.Employee;
import com.example.projecttest.entity.EmployeeItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Duration;
import java.util.List;

@Mapper(imports = {Duration.class}, uses = {EmployeeMapper.class})
public interface EmployeeItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", source = "employee")
    @Mapping(target = "workingHour", expression = "java(Duration.between(dto.arrivalDate(), dto.exitDate()).toHours() > 8 ? 8 : Duration.between(dto.arrivalDate(), dto.exitDate()).toHours())")
    EmployeeItem newEmployeeItem(CreateEmployeeItem dto, Employee employee);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "workingHour", expression = "java(Duration.between(dto.arrivalDate(), dto.exitDate()).toHours() > 8 ? 8 : Duration.between(dto.arrivalDate(), dto.exitDate()).toHours())")
    @Mapping(target = "arrivalDate", source = "dto.arrivalDate")
    @Mapping(target = "exitDate", source = "dto.exitDate")
    void asUpdateEmployee(UpdateEmployeeItem dto, @MappingTarget EmployeeItem employeeItem);

    EmployeeItemVm asEmployeeItemVm(EmployeeItem employeeItem);

    List<EmployeeItemVm> asEmployeeItemList(List<EmployeeItem> employeeItems);

}
