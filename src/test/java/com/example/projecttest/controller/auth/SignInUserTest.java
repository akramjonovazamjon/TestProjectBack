package com.example.projecttest.controller.auth;

import com.example.projecttest.CommonIntegrationTest;
import com.example.projecttest.dto.auth.LoginDto;
import com.example.projecttest.dto.auth.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("user sign in ( POST /auth/sign-in )")
public class SignInUserTest extends CommonIntegrationTest {


    @Test
    @DisplayName("should user sign in status code 200")
    void shouldUserSignIn() throws Exception {

        testDataHelperAuth.signUpUserRequest(new UserDto("Azamjon", "azam", "123"));

        ResultActions resultActions = testDataHelperAuth.signInUserRequest(new LoginDto("azam", "123"));

        resultActions.andExpect(status().isOk());

    }

    @Test
    @DisplayName("should fail user sign in because of username or password wrong status code 409")
    void shouldFailUserSignInByUsernameOrPassword() throws Exception {

        testDataHelperAuth.signUpUserRequest(new UserDto("Azamjon", "azam", "123"));

        ResultActions resultActions = testDataHelperAuth.signInUserRequest(new LoginDto("azam1", "12345"));

        resultActions.andExpect(status().isConflict());

    }

    @Test
    @DisplayName("should fail user sign in because of required fields null status code 409")
    void shouldFailUserSignInByRequiredFields() throws Exception {

        ResultActions resultActions = testDataHelperAuth.signInUserRequest(new LoginDto(null, "123"));

        resultActions.andExpect(status().isBadRequest());

    }

}
