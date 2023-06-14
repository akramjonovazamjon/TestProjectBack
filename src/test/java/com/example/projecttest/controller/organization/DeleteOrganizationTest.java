package com.example.projecttest.controller.organization;

import com.example.projecttest.CommonIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Delete organization ( DELETE /organizations )")
public class DeleteOrganizationTest extends CommonIntegrationTest {

    @Test
    @DisplayName("should delete organization status 200")
    void shouldDeleteOrganization() throws Exception {

        MockHttpServletRequestBuilder requestBuilderForAdd = MockMvcRequestBuilders
                .post("/organizations")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(objectMapper.writeValueAsString(Map.of("name", "Org1")));

        MockHttpServletRequestBuilder requestBuilderForDelete = MockMvcRequestBuilders
                .delete("/organizations/1");

        mockMvc.perform(requestBuilderForAdd);

        ResultActions resultActions = mockMvc.perform(requestBuilderForDelete);

        resultActions.andExpect(status().isOk());

    }

}
