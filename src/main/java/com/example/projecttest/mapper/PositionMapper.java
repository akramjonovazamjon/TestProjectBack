package com.example.projecttest.mapper;

import com.example.projecttest.controller.vm.PositionVm;
import com.example.projecttest.dto.position.CreatePosition;
import com.example.projecttest.entity.Organization;
import com.example.projecttest.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PositionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "organization", source = "organization")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "salary", source = "dto.salary")
    Position newPosition(CreatePosition dto, Organization organization);

    @Mapping(target = "organization", source = "position.organization.name")
    PositionVm asPositionVm(Position position);

    List<PositionVm> asPositionList(List<Position> positions);
}
