package com.example.projecttest.controller.organization;

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

@DisplayName("Create a new organization ( POST /organizations )")
public class CreateOrganizationTest extends CommonIntegrationTest {


    @Test
    @DisplayName("Should create a organizations with 200 status")
    void shouldCreateOrganizations() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/organizations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("name", "Org1")));


        ResultActions resultActions = mockMvc.perform(requestBuilder);


        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.name").value("Org1"))
                .andExpect(jsonPath("$.result.id").value(1));
    }

    @Test
    @DisplayName("Should not create a organizations with 409 status")
    void shouldFailNameDuplicate() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/organizations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Map.of("name", "Org1")));


        mockMvc.perform(requestBuilder);
        ResultActions resultActions = mockMvc.perform(requestBuilder);


        resultActions
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("Should not create a organizations with 400 status")
    void shouldFailRequiredFieldNull() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/organizations")
                .contentType(MediaType.APPLICATION_JSON);


        ResultActions resultActions = mockMvc.perform(requestBuilder);


        resultActions
                .andExpect(status().isBadRequest());
    }


}
