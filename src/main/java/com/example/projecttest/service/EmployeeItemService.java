package com.example.projecttest.service;

import com.example.projecttest.controller.vm.EmployeeWorkingSalary;
import com.example.projecttest.dto.employee.CreateEmployeeItem;
import com.example.projecttest.dto.employee.YearMonthDto;
import com.example.projecttest.entity.Employee;
import com.example.projecttest.entity.EmployeeItem;
import com.example.projecttest.mapper.EmployeeItemMapper;
import com.example.projecttest.repository.EmployeeItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeItemService {
    private final EmployeeItemRepository repository;
    private final EmployeeItemMapper mapper;
    private final EmployeeService employeeService;

    public EmployeeItem create(Long empId, CreateEmployeeItem dto) {
        Employee employee = employeeService.getById(empId);

        EmployeeItem employeeItem = mapper.newEmployeeItem(dto, employee);

        return repository.save(employeeItem);
    }

    public List<EmployeeItem> getEmployeeMonthlyWorkingHours(Long empId, YearMonthDto dto) {
        LocalDateTime date1 = LocalDateTime.of(dto.year(), dto.month(), 1, 0, 0, 0);
        LocalDateTime date2 = date1.plusMonths(1);
        return repository.findAllByEmployeeIdAndArrivalDateBetween(empId, date1, date2);
    }

    public EmployeeWorkingSalary getEmployeeMonthlySalary(Long empId, YearMonthDto dto) {
        LocalDateTime date1 = LocalDateTime.of(dto.year(), dto.month(), 1, 0, 0, 0);
        LocalDateTime date2 = date1.plusMonths(1);
        Long monthlyWorkingHours = repository.monthlyWorkingHours(empId, date1, date2);
        monthlyWorkingHours = monthlyWorkingHours == null ? 0 : monthlyWorkingHours;
        Employee employee = employeeService.getById(empId);
        return new EmployeeWorkingSalary(empId, employee.getFullName(), employee.getSalary(), employee.getPhoneNumber(),
                employee.getOrganization().getName(), YearMonth.of(dto.year(), dto.month()), monthlyWorkingHours * employee.getSalary().longValue(), monthlyWorkingHours);
    }
}
