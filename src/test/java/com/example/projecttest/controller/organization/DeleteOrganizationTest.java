package com.example.projecttest.controller.organization;

import com.example.projecttest.CommonIntegrationTest;
import com.example.projecttest.dto.organization.CreateOrganization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Delete organization ( DELETE /organizations )")
public class DeleteOrganizationTest extends CommonIntegrationTest {

    @Test
    @DisplayName("should delete organization status 200")
    void shouldDeleteOrganization() throws Exception {

        testDataHelperOrganization.createOrganizationRequest(new CreateOrganization("Org1"));

        ResultActions resultActions = testDataHelperOrganization.deleteOrganizationRequest(1l);

        resultActions.andExpect(status().isOk());

    }

}
