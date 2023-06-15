package com.example.projecttest.controller.auth;

import com.example.projecttest.CommonIntegrationTest;
import com.example.projecttest.dto.auth.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("user sign up ( POST /auth/sign-up )")
public class SignUpUserTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should user sign up status code 201")
    void shouldUserSignUp() throws Exception {

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserDto("A'zam", "azam", "123")))
        );

        resultActions.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("should fail user sign up because of user exist status code 409")
    void shouldFailUserSignUpByUserExist() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserDto("A'zam", "azam", "123")))
        );

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserDto("Ali", "azam", "12300")))
        );

        resultActions.andExpect(status().isConflict());
    }

    @Test
    @DisplayName("should fail user sign up because of required fields null status code 409")
    void shouldFailUserSignUpByRequiredFields() throws Exception {

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UserDto(null, "azam", "12300")))
        );

        resultActions.andExpect(status().isBadRequest());
    }

}
