package com.example.projecttest.controller;

import com.example.projecttest.controller.vm.EmployeeVm;
import com.example.projecttest.dto.ResponseData;
import com.example.projecttest.dto.employee.CreateEmployee;
import com.example.projecttest.dto.employee.UpdateEmployee;
import com.example.projecttest.entity.Employee;
import com.example.projecttest.mapper.EmployeeMapper;
import com.example.projecttest.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;
    private final EmployeeMapper mapper;

    @PostMapping
    public ResponseData<EmployeeVm> create(@RequestBody @Valid CreateEmployee dto) {
        Employee employee = service.create(dto);
        return ResponseData.of(mapper.asEmployeeVm(employee));
    }

    @GetMapping
    public ResponseData<List<EmployeeVm>> getAll(Pageable pageable) {
        List<Employee> employees = service.getAll(pageable);
        return ResponseData.of(mapper.asEmployeeList(employees));
    }

    @GetMapping("/{id}")
    public ResponseData<EmployeeVm> getById(@PathVariable Long id) {
        Employee employee = service.getById(id);
        return ResponseData.of(mapper.asEmployeeVm(employee));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateEmployee dto) {
        service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
