package com.example.projecttest.controller.position;

import com.example.projecttest.CommonIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("create new position ( POST /positions )")
public class CreatePositionTest extends CommonIntegrationTest {

    @Test
    @DisplayName("should create position status 201")
    void shouldCreatePosition() throws Exception {

        MockHttpServletRequestBuilder requestBuilderForOrgAdd = MockMvcRequestBuilders
                .post("/organizations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("name", "Exadel")));

        mockMvc.perform(requestBuilderForOrgAdd);

        MockHttpServletRequestBuilder requestBuilderForPositionAdd = MockMvcRequestBuilders
                .post("/positions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("name", "Java Developer", "salary", 1500, "orgId", 1)));

        ResultActions resultActions = mockMvc.perform(requestBuilderForPositionAdd);

        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.name").value("Java Developer"))
                .andExpect(jsonPath("$.result.salary").value(1500))
                .andExpect(jsonPath("$.result.organization").value("Exadel"));
    }

    @Test
    @DisplayName("should fail create position because of organization not found by id status 409")
    void shouldFailByOrganizationIdCreatePosition() throws Exception {

        MockHttpServletRequestBuilder requestBuilderForPositionAdd = MockMvcRequestBuilders
                .post("/positions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("name", "Java Developer", "salary", 1500, "orgId", 1)));

        ResultActions resultActions = mockMvc.perform(requestBuilderForPositionAdd);

        resultActions
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("should fail create position because of organization not found by id status 409")
    void shouldFailByDuplicateCreatePosition() throws Exception {

        MockHttpServletRequestBuilder requestBuilderForOrgAdd = MockMvcRequestBuilders
                .post("/organizations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("name", "Exadel")));

        mockMvc.perform(requestBuilderForOrgAdd);

        MockHttpServletRequestBuilder requestBuilderForPositionAdd = MockMvcRequestBuilders
                .post("/positions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("name", "Java Developer", "salary", 1500, "orgId", 1)));

        mockMvc.perform(requestBuilderForPositionAdd);
        ResultActions resultActions = mockMvc.perform(requestBuilderForPositionAdd);

        resultActions
                .andExpect(status().isConflict());
    }


}
