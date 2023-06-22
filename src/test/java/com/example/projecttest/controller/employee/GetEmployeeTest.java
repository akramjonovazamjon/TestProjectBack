package com.example.projecttest.controller.employee;

import com.example.projecttest.CommonIntegrationTest;
import com.example.projecttest.dto.employee.CreateEmployee;
import com.example.projecttest.dto.organization.CreateOrganization;
import com.example.projecttest.dto.position.CreatePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("get employees ( GET /employees )")
public class GetEmployeeTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should get employees status code 200")
    void shouldGetEmployees() throws Exception {

        testDataHelperOrganization.createOrganizationRequest(new CreateOrganization("Exadel"));

        testDataHelperPosition.createPositionRequest(new CreatePosition("Java Developer", BigDecimal.valueOf(5000), 1L));

        testDataHelperEmployee.createEmployeeRequest(new CreateEmployee("A'zamjon", "9175", 1L));

        testDataHelperEmployee.createEmployeeRequest(new CreateEmployee("A'zamjon", "9000", 1L));

        ResultActions resultActions = testDataHelperEmployee.getEmployeeRequest();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").isArray())
                .andExpect(jsonPath("$.result", hasSize(2)));

    }

    @Test
    @DisplayName("should get employee by id status code 200")
    void shouldGetEmployeeById() throws Exception {

        testDataHelperOrganization.createOrganizationRequest(new CreateOrganization("Exadel"));

        testDataHelperPosition.createPositionRequest(new CreatePosition("Java Developer", BigDecimal.valueOf(5000), 1L));

        testDataHelperEmployee.createEmployeeRequest(new CreateEmployee("A'zamjon", "9175", 1l));

        ResultActions resultActions = testDataHelperEmployee.getEmployeeByIdRequest();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(1))
                .andExpect(jsonPath("$.result.fullName").value("A'zamjon"))
                .andExpect(jsonPath("$.result.phoneNumber").value("9175"))
                .andExpect(jsonPath("$.result.position").value("Java Developer"))
                .andExpect(jsonPath("$.result.salary").value(5000))
                .andExpect(jsonPath("$.result.organization").value("Exadel"));

    }

}
