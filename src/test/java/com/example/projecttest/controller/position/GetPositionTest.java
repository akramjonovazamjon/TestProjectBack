package com.example.projecttest.controller.position;

import com.example.projecttest.CommonIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("get position ( GET /positions )")
public class GetPositionTest extends CommonIntegrationTest {


    @Test
    @DisplayName("get all positions status 200")
    void shouldGetAllPositions() throws Exception {

        MockHttpServletRequestBuilder requestBuilderForOrgAdd = MockMvcRequestBuilders
                .post("/organizations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("name", "Exadel")));

        mockMvc.perform(requestBuilderForOrgAdd);

        MockHttpServletRequestBuilder requestBuilderForPositionAdd1 = MockMvcRequestBuilders
                .post("/positions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("name", "Java Developer", "salary", 1500, "orgId", 1)));

        MockHttpServletRequestBuilder requestBuilderForPositionAdd2 = MockMvcRequestBuilders
                .post("/positions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("name", "C# Developer", "salary", 1500, "orgId", 1)));

        mockMvc.perform(requestBuilderForPositionAdd1);
        mockMvc.perform(requestBuilderForPositionAdd2);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/positions");

        ResultActions resultActions = mockMvc.perform(requestBuilder);

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result", hasSize(2)));

    }


}
