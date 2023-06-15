package com.example.projecttest.controller.employee;

import com.example.projecttest.CommonIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("update employee ( PUT /employees/{id} )")
public class UpdateEmployeeTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should update employee status code 200")
    void shouldUpdateEmployee() throws Exception {

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
                            .content(objectMapper.writeValueAsString(Map.of("name", "C#", "salary", 5000, "orgId", 1))));

            mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/positions")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("name", "Java Developer", "salary", 7000, "orgId", 1))));
        }

        //create employee
        {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .post("/employees")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("fullName", "A'zamjon", "phoneNumber", "9175", "positionId", 1))));
        }

        //update employee
        {
            mockMvc.perform(
                    MockMvcRequestBuilders
                            .put("/employees/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(Map.of("fullName", "Akramjonov", "phoneNumber", "9175", "positionId", 2))));

            ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/employees/1"));

            resultActions
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.result.fullName").value("Akramjonov"))
                    .andExpect(jsonPath("$.result.id").value(1))
                    .andExpect(jsonPath("$.result.position").value("Java Developer"))
                    .andExpect(jsonPath("$.result.salary").value(7000))
                    .andExpect(jsonPath("$.result.organization").value("Exadel"));
        }
    }

}
