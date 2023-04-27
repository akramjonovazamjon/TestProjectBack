package com.example.projecttest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "salary", nullable = false)
    private BigDecimal salary;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
