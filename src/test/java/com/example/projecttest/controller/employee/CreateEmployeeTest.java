package com.example.projecttest.controller.employee;

import com.example.projecttest.CommonIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("create new employee ( POST /employees )")
public class CreateEmployeeTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should create employee status 201")
    void shouldCreateEmployee() throws Exception {

        // create organization
        {
            MockHttpServletRequestBuilder requestBuilderForOrgAdd = MockMvcRequestBuilders
                    .post("/organizations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(Map.of("name", "Exadel")));

            mockMvc.perform(requestBuilderForOrgAdd);
        }

        //create position
        {
            MockHttpServletRequestBuilder requestBuilderForPositionAdd = MockMvcRequestBuilders
                    .post("/positions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(Map.of("name", "Java Developer", "salary", 5000, "orgId", 1)));

            mockMvc.perform(requestBuilderForPositionAdd);
        }

        //create employee
        {
            MockHttpServletRequestBuilder requestBuilderForPositionAdd = MockMvcRequestBuilders
                    .post("/employees")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(Map.of("fullName", "A'zamjon", "phoneNumber", "9175", "positionId", 1)));

            ResultActions resultActions = mockMvc.perform(requestBuilderForPositionAdd);

            resultActions
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.result.id").value(1))
                    .andExpect(jsonPath("$.result.fullName").value("A'zamjon"))
                    .andExpect(jsonPath("$.result.phoneNumber").value("9175"))
                    .andExpect(jsonPath("$.result.position").value("Java Developer"))
                    .andExpect(jsonPath("$.result.salary").value(5000))
                    .andExpect(jsonPath("$.result.organization").value("Exadel"));
        }
    }

    @Test
    @DisplayName("should fail by phoneNumber duplicate create employee status 409")
    void shouldFailByPhoneNumberDuplicateCreateEmployee() throws Exception {

        // create organization
        {
            MockHttpServletRequestBuilder requestBuilderForOrgAdd = MockMvcRequestBuilders
                    .post("/organizations")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(Map.of("name", "Exadel")));

            mockMvc.perform(requestBuilderForOrgAdd);
        }

        //create position
        {
            MockHttpServletRequestBuilder requestBuilderForPositionAdd = MockMvcRequestBuilders
                    .post("/positions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(Map.of("name", "Java Developer", "salary", 5000, "orgId", 1)));

            mockMvc.perform(requestBuilderForPositionAdd);
        }

        //create employee
        {
            MockHttpServletRequestBuilder requestBuilderForPositionAdd = MockMvcRequestBuilders
                    .post("/employees")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(Map.of("fullName", "A'zamjon", "phoneNumber", "9175", "positionId", 1)));

            mockMvc.perform(requestBuilderForPositionAdd);
            ResultActions resultActions = mockMvc.perform(requestBuilderForPositionAdd);

            resultActions
                    .andExpect(status().isConflict());
        }
    }


}
