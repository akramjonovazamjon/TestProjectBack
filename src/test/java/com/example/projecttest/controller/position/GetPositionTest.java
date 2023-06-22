package com.example.projecttest.controller.position;

import com.example.projecttest.CommonIntegrationTest;
import com.example.projecttest.dto.organization.CreateOrganization;
import com.example.projecttest.dto.position.CreatePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("get position ( GET /positions )")
public class GetPositionTest extends CommonIntegrationTest {


    @Test
    @DisplayName("get all positions status 200")
    void shouldGetAllPositions() throws Exception {

        testDataHelperOrganization.createOrganizationRequest(new CreateOrganization("Exadel"));

        testDataHelperPosition.createPositionRequest(new CreatePosition("Java Developer", BigDecimal.valueOf(1500), 1L));

        testDataHelperPosition.createPositionRequest(new CreatePosition("C# Developer", BigDecimal.valueOf(1000), 1L));

        ResultActions resultActions = testDataHelperPosition.getPositionRequest();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result", hasSize(2)));

    }


}
