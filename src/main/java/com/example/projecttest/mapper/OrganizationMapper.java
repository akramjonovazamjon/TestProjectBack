package com.example.projecttest.mapper;

import com.example.projecttest.controller.vm.OrganizationVm;
import com.example.projecttest.entity.Organization;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrganizationMapper {
    OrganizationVm asOrganizationVm(Organization organization);

    List<OrganizationVm> asOrganizationList(List<Organization> organizations);

}
