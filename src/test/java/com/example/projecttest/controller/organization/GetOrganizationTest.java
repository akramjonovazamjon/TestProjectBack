package com.example.projecttest.controller.organization;

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

@DisplayName("Get all organization ( GET /organizations )")
public class GetOrganizationTest extends CommonIntegrationTest {

    @Test
    @DisplayName("should extract all organizations status 200")
    void shouldExtractAllOrganizations() throws Exception {

        MockHttpServletRequestBuilder requestBuilderForAdd = MockMvcRequestBuilders
                .post("/organizations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("name", "Org1")));

        MockHttpServletRequestBuilder requestBuilderForGet = MockMvcRequestBuilders
                .get("/organizations");

        mockMvc.perform(requestBuilderForAdd);
        ResultActions resultActions = mockMvc.perform(requestBuilderForGet);

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result", hasSize(1)))
                .andExpect(jsonPath("$.result[0].name").value("Org1"))
                .andExpect(jsonPath("$.result[0].id").value(1));
    }

    @Test
    @DisplayName("should fail extract all organizations because of wrong url status 404")
    void shouldFailExtractAllOrganizations() throws Exception {

        MockHttpServletRequestBuilder requestBuilderForGet = MockMvcRequestBuilders
                .get("/organizationss");

        ResultActions resultActions = mockMvc.perform(requestBuilderForGet);

        resultActions
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should get organization by id status 200")
    void shouldGetOrganizationById() throws Exception {

        MockHttpServletRequestBuilder requestBuilderForAdd = MockMvcRequestBuilders
                .post("/organizations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("name", "Org1")));

        mockMvc.perform(requestBuilderForAdd);

        MockHttpServletRequestBuilder requestBuilderForGetById = MockMvcRequestBuilders
                .get("/organizations/1");

        ResultActions resultActions = mockMvc.perform(requestBuilderForGetById);

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.name").value("Org1"))
                .andExpect(jsonPath("$.result.id").value(1));

    }

}
