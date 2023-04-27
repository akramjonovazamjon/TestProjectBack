package com.example.projecttest.controller;

import com.example.projecttest.controller.vm.EmployeeItemVm;
import com.example.projecttest.controller.vm.EmployeeWorkingSalary;
import com.example.projecttest.dto.ResponseData;
import com.example.projecttest.dto.employee.CreateEmployeeItem;
import com.example.projecttest.dto.employee.UpdateEmployeeItem;
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

    @GetMapping
    public ResponseData<List<EmployeeItemVm>> getMonthlyWorkingStatistics(@PathVariable Long empId, @RequestParam int year, @RequestParam int month) {
        List<EmployeeItem> employeeMonthlyWorkingHours = service.getEmployeeMonthlyWorkingHours(empId, year, month);
        return ResponseData.of(mapper.asEmployeeItemList(employeeMonthlyWorkingHours));
    }

    @GetMapping("/salary")
    public ResponseData<EmployeeWorkingSalary> getEmployeeSalary(@PathVariable Long empId, @RequestParam int year, @RequestParam int month) {
        EmployeeWorkingSalary employeeMonthlySalary = service.getEmployeeMonthlySalary(empId, year, month);
        return ResponseData.of(employeeMonthlySalary);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateEmployeeItem dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
