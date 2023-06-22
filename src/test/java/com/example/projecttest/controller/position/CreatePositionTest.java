package com.example.projecttest.controller.position;

import com.example.projecttest.CommonIntegrationTest;
import com.example.projecttest.dto.organization.CreateOrganization;
import com.example.projecttest.dto.position.CreatePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("create new position ( POST /positions )")
public class CreatePositionTest extends CommonIntegrationTest {

    @Test
    @DisplayName("should create position status 201")
    void shouldCreatePosition() throws Exception {

        testDataHelperOrganization.createOrganizationRequest(new CreateOrganization("Exadel"));

        ResultActions resultActions = testDataHelperPosition.createPositionRequest(new CreatePosition("Java Developer", BigDecimal.valueOf(1500), 1L));

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

        ResultActions resultActions = testDataHelperPosition.createPositionRequest(new CreatePosition("Java Developer", BigDecimal.valueOf(1500), 1L));

        resultActions.andExpect(status().isConflict());
    }

    @Test
    @DisplayName("should fail create position because of duplicate value status 409")
    void shouldFailByDuplicateCreatePosition() throws Exception {

        testDataHelperOrganization.createOrganizationRequest(new CreateOrganization("Exadel"));

        testDataHelperPosition.createPositionRequest(new CreatePosition("Java Developer", BigDecimal.valueOf(1500), 1L));

        ResultActions resultActions = testDataHelperPosition.createPositionRequest(new CreatePosition("Java Developer", BigDecimal.valueOf(1500), 1L));

        resultActions.andExpect(status().isConflict());
    }


}
