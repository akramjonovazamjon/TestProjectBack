package com.example.projecttest.controller.organization;

import com.example.projecttest.CommonIntegrationTest;
import com.example.projecttest.dto.organization.CreateOrganization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Get all organization ( GET /organizations )")
public class GetOrganizationTest extends CommonIntegrationTest {

    @Test
    @DisplayName("should extract all organizations status 200")
    void shouldExtractAllOrganizations() throws Exception {

        testDataHelperOrganization.createOrganizationRequest(new CreateOrganization("Org1"));

        ResultActions resultActions = testDataHelperOrganization.getOrganizationRequest();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result", hasSize(1)))
                .andExpect(jsonPath("$.result[0].name").value("Org1"))
                .andExpect(jsonPath("$.result[0].id").value(1));
    }

    @Test
    @DisplayName("should get organization by id status 200")
    void shouldGetOrganizationById() throws Exception {

        testDataHelperOrganization.createOrganizationRequest(new CreateOrganization("Org1"));

        ResultActions resultActions = testDataHelperOrganization.getOrganizationByIdRequest(1l);

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.name").value("Org1"))
                .andExpect(jsonPath("$.result.id").value(1));
    }

}
