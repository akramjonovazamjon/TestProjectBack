package com.example.projecttest.controller.employee.data;

import com.example.projecttest.dto.employee.CreateEmployee;
import com.example.projecttest.dto.employee.UpdateEmployee;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TestDataHelperEmployee {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    public ResultActions createEmployeeRequest(CreateEmployee dto) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
    }

    public ResultActions getEmployeeRequest() throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.get("/employees"));
    }

    public ResultActions getEmployeeByIdRequest() throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.get("/employees/1"));
    }

    public ResultActions updateEmployeeRequest(UpdateEmployee dto) throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)));

        return mockMvc.perform(MockMvcRequestBuilders.get("/employees/1"));
    }

}
