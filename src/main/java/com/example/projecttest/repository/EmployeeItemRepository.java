package com.example.projecttest.repository;

import com.example.projecttest.entity.EmployeeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeItemRepository extends JpaRepository<EmployeeItem, Long> {

    List<EmployeeItem> findAllByEmployeeIdAndArrivalDateBetween(Long employeeId, LocalDateTime date1, LocalDateTime date2);

    @Query("SELECT SUM(e.workingHour) FROM EmployeeItem e WHERE e.employee.id = ?1 AND e.arrivalDate BETWEEN ?2 and ?3")
    Long monthlyWorkingHours(Long empId, LocalDateTime date1, LocalDateTime date2);
}
