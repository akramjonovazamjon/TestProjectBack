package com.example.projecttest.controller;

import com.example.projecttest.controller.vm.EmployeeItemVm;
import com.example.projecttest.controller.vm.EmployeeWorkingSalary;
import com.example.projecttest.dto.ResponseData;
import com.example.projecttest.dto.employee.CreateEmployeeItem;
import com.example.projecttest.dto.employee.YearMonthDto;
import com.example.projecttest.entity.EmployeeItem;
import com.example.projecttest.mapper.EmployeeItemMapper;
import com.example.projecttest.service.EmployeeItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees/{empId}/items")
public class EmployeeItemController {

    private final EmployeeItemService service;
    private final EmployeeItemMapper mapper;

    @PostMapping
    public ResponseData<EmployeeItemVm> create(@PathVariable Long empId, @Valid @RequestBody CreateEmployeeItem dto) {
        EmployeeItem employeeItem = service.create(empId, dto);
        return ResponseData.of(mapper.asEmployeeItemVm(employeeItem));
    }

    @PutMapping
    public ResponseData<List<EmployeeItemVm>> getMonthlyWorkingStatistics(@PathVariable Long empId, @RequestBody YearMonthDto dto) {
        List<EmployeeItem> employeeMonthlyWorkingHours = service.getEmployeeMonthlyWorkingHours(empId, dto);
        return ResponseData.of(mapper.asEmployeeItemList(employeeMonthlyWorkingHours));
    }

    @PutMapping("/salary")
    public ResponseData<EmployeeWorkingSalary> getEmployeeSalary(@PathVariable Long empId, @RequestBody YearMonthDto dto) {
        EmployeeWorkingSalary employeeMonthlySalary = service.getEmployeeMonthlySalary(empId, dto);
        return ResponseData.of(employeeMonthlySalary);
    }
}
