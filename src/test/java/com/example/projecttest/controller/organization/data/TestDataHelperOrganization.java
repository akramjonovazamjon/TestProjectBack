package com.example.projecttest.controller.organization.data;

import com.example.projecttest.dto.organization.CreateOrganization;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
@RequiredArgsConstructor
public class TestDataHelperOrganization {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    public ResultActions createOrganizationRequest(CreateOrganization dto) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
    }

    public ResultActions deleteOrganizationRequest(Long id) throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.delete("/organizations/" + id));

    }

    public ResultActions getOrganizationByIdRequest(Long id) throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.get("/organizations/" + id));

    }

    public ResultActions getOrganizationRequest() throws Exception {

        return mockMvc.perform(MockMvcRequestBuilders.get("/organizations"));

    }


}
