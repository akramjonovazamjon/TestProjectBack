package com.example.projecttest.controller.auth;

import com.example.projecttest.CommonIntegrationTest;
import com.example.projecttest.dto.auth.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("user sign up ( POST /auth/sign-up )")
public class SignUpUserTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should user sign up status code 201")
    void shouldUserSignUp() throws Exception {

        ResultActions resultActions = testDataHelperAuth.signUpUserRequest(new UserDto("Azamjon", "azam", "123"));

        resultActions.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("should fail user sign up because of user exist status code 409")
    void shouldFailUserSignUpByUserExist() throws Exception {

        testDataHelperAuth.signUpUserRequest(new UserDto("Azamjon", "azam", "123"));

        ResultActions resultActions = testDataHelperAuth.signUpUserRequest(new UserDto("Ali", "azam", "12300"));

        resultActions.andExpect(status().isConflict());
    }

    @Test
    @DisplayName("should fail user sign up because of required fields null status code 409")
    void shouldFailUserSignUpByRequiredFields() throws Exception {

        ResultActions resultActions = testDataHelperAuth.signUpUserRequest(new UserDto(null, "azam", "123"));

        resultActions.andExpect(status().isBadRequest());
    }

}
