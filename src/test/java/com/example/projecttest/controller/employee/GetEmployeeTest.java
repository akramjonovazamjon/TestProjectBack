package com.example.projecttest.controller.employee;

import com.example.projecttest.CommonIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("get employees ( GET /employees )")
public class GetEmployeeTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should get employees status code 200")
    void shouldGetEmployees() throws Exception {

        // create organization
        {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/organizations")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("name", "Exadel"))));
        }

        //create position
        {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/positions")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("name", "Java Developer", "salary", 5000, "orgId", 1))));
        }

        //create employee
        {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/employees")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("fullName", "A'zamjon", "phoneNumber", "9175", "positionId", 1))));

            mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/employees")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("fullName", "A'zamjon", "phoneNumber", "9000", "positionId", 1))));
        }

        //get all employee
        {

            ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/employees"));

            resultActions
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.result").isArray())
                    .andExpect(jsonPath("$.result", hasSize(2)));
        }
    }

    @Test
    @DisplayName("should get employee by id status code 200")
    void shouldGetEmployeeById() throws Exception {

        // create organization
        {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/organizations")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("name", "Exadel"))));
        }

        //create position
        {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/positions")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("name", "Java Developer", "salary", 5000, "orgId", 1))));
        }

        //create employee
        {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/employees")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("fullName", "A'zamjon", "phoneNumber", "9175", "positionId", 1))));
        }

        //get employee by id
        {
            ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/employees/1"));

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

}
