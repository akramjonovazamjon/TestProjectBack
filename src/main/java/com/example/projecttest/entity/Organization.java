package com.example.projecttest.entity;

import com.example.projecttest.dto.organization.CreateOrganization;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public static Organization of(CreateOrganization dto) {
        return Organization.builder()
                .name(dto.name())
                .build();
    }
}
