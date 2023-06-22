package com.example.projecttest.controller.employee;

import com.example.projecttest.CommonIntegrationTest;
import com.example.projecttest.dto.employee.CreateEmployee;
import com.example.projecttest.dto.employee.UpdateEmployee;
import com.example.projecttest.dto.organization.CreateOrganization;
import com.example.projecttest.dto.position.CreatePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("update employee ( PUT /employees/{id} )")
public class UpdateEmployeeTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should update employee status code 200")
    void shouldUpdateEmployee() throws Exception {

        testDataHelperOrganization.createOrganizationRequest(new CreateOrganization("Exadel"));

        testDataHelperPosition.createPositionRequest(new CreatePosition("Java Developer", BigDecimal.valueOf(7000), 1L));

        testDataHelperPosition.createPositionRequest(new CreatePosition("C#", BigDecimal.valueOf(5000), 1L));

        testDataHelperEmployee.createEmployeeRequest(new CreateEmployee("A'zamjon", "9175", 1L));

        ResultActions resultActions = testDataHelperEmployee.updateEmployeeRequest(new UpdateEmployee("Akramjonov", "9175", 2l));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.fullName").value("Akramjonov"))
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.position").value("Java Developer"))
                .andExpect(jsonPath("$.result.salary").value(7000))
                .andExpect(jsonPath("$.result.organization").value("Exadel"));

    }

}
