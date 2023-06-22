package com.example.projecttest.controller.organization;

import com.example.projecttest.CommonIntegrationTest;
import com.example.projecttest.dto.organization.CreateOrganization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Create a new organization ( POST /organizations )")
public class CreateOrganizationTest extends CommonIntegrationTest {


    @Test
    @DisplayName("Should create a organizations with 200 status")
    void shouldCreateOrganizations() throws Exception {

        ResultActions resultActions = testDataHelperOrganization.createOrganizationRequest(new CreateOrganization("Org1"));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.name").value("Org1"))
                .andExpect(jsonPath("$.result.id").value(1));
    }

    @Test
    @DisplayName("Should not create a organizations with 409 status")
    void shouldFailNameDuplicate() throws Exception {

        testDataHelperOrganization.createOrganizationRequest(new CreateOrganization("Org1"));

        ResultActions resultActions = testDataHelperOrganization.createOrganizationRequest(new CreateOrganization("Org1"));

        resultActions.andExpect(status().isConflict());
    }

    @Test
    @DisplayName("Should not create a organizations with 400 status")
    void shouldFailRequiredFieldNull() throws Exception {

        ResultActions resultActions = testDataHelperOrganization.createOrganizationRequest(null);

        resultActions.andExpect(status().isBadRequest());
    }

}
