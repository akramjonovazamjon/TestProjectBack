package com.example.projecttest.service;

import com.example.projecttest.controller.vm.EmployeeWorkingSalary;
import com.example.projecttest.dto.employee.CreateEmployeeItem;
import com.example.projecttest.dto.employee.UpdateEmployeeItem;
import com.example.projecttest.entity.Employee;
import com.example.projecttest.entity.EmployeeItem;
import com.example.projecttest.exception.employee.EmployeeItemNotFoundException;
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

    public List<EmployeeItem> getEmployeeMonthlyWorkingHours(Long empId, int year, int month) {
        LocalDateTime date1 = LocalDateTime.of(year, month, 1, 0, 0, 0);
        LocalDateTime date2 = date1.plusMonths(1);
        return repository.findAllByEmployeeIdAndArrivalDateBetween(empId, date1, date2);
    }

    public void update(Long id, UpdateEmployeeItem dto) {
        EmployeeItem employeeItem = repository.findById(id).orElseThrow(EmployeeItemNotFoundException::new);
        mapper.asUpdateEmployee(dto, employeeItem);
        repository.save(employeeItem);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public EmployeeWorkingSalary getEmployeeMonthlySalary(Long empId, int year, int month) {
        LocalDateTime date1 = LocalDateTime.of(year, month, 1, 0, 0, 0);
        LocalDateTime date2 = date1.plusMonths(1);
        Long monthlyWorkingHours = repository.monthlyWorkingHours(empId, date1, date2);
        monthlyWorkingHours = monthlyWorkingHours == null ? 0 : monthlyWorkingHours;
        Employee employee = employeeService.getById(empId);
        Double salary = (monthlyWorkingHours.doubleValue() * employee.getPosition().getSalary().doubleValue()) / 160;
        return new EmployeeWorkingSalary(empId, employee.getFullName(), employee.getPhoneNumber(),
                employee.getPosition().getName(), employee.getPosition().getOrganization().getName(),
                YearMonth.of(year, month), salary, monthlyWorkingHours
        );
    }
}
