package com.example.projecttest.controller.position.data;

import com.example.projecttest.dto.position.CreatePosition;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
@RequiredArgsConstructor
public class TestDataHelperPosition {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    public ResultActions createPositionRequest(CreatePosition dto) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/positions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
    }

    public ResultActions getPositionRequest() throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.get("/positions"));
    }

}
