package com.example.projecttest.controller;

import com.example.projecttest.controller.vm.OrganizationVm;
import com.example.projecttest.dto.ResponseData;
import com.example.projecttest.dto.organization.CreateOrganization;
import com.example.projecttest.entity.Organization;
import com.example.projecttest.mapper.OrganizationMapper;
import com.example.projecttest.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService service;
    private final OrganizationMapper mapper;

    @PostMapping
    public ResponseData<OrganizationVm> create(@RequestBody @Valid CreateOrganization dto) {
        Organization organization = service.create(dto);
        return ResponseData.of(mapper.asOrganizationVm(organization));
    }

    @GetMapping
    public ResponseData<List<OrganizationVm>> getAll(Pageable pageable) {
        List<Organization> organizations = service.getAll(pageable);
        return ResponseData.of(mapper.asOrganizationList(organizations));
    }

    @GetMapping("/{id}")
    public ResponseData<OrganizationVm> getById(@PathVariable Long id) {
        Organization organization = service.getById(id);
        return ResponseData.of(mapper.asOrganizationVm(organization));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
