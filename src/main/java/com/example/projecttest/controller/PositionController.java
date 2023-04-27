package com.example.projecttest.controller;

import com.example.projecttest.controller.vm.PositionVm;
import com.example.projecttest.dto.ResponseData;
import com.example.projecttest.dto.position.CreatePosition;
import com.example.projecttest.entity.Position;
import com.example.projecttest.mapper.PositionMapper;
import com.example.projecttest.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/positions")
public class PositionController {

    private final PositionService service;
    private final PositionMapper mapper;

    @PostMapping
    public ResponseData<PositionVm> create(@RequestBody @Valid CreatePosition dto) {
        Position position = service.create(dto);
        return ResponseData.of(mapper.asPositionVm(position));
    }

    @GetMapping
    public ResponseData<List<PositionVm>> getAll() {
        List<Position> positions = service.getAll();
        return ResponseData.of(mapper.asPositionList(positions));
    }

    @GetMapping("/{id}")
    public ResponseData<PositionVm> getById(@PathVariable Long id) {
        Position position = service.getById(id);
        return ResponseData.of(mapper.asPositionVm(position));
    }

}
