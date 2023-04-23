package com.example.projecttest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "employee_items")
public class EmployeeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "arrival_date", nullable = false)
    private LocalDateTime arrivalDate;
    @Column(name = "exit_date", nullable = false)
    private LocalDateTime exitDate;
    @Column(name = "working_hour", nullable = false)
    private Long workingHour;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
