package com.example.projecttest.controller.auth.data;

import com.example.projecttest.dto.auth.LoginDto;
import com.example.projecttest.dto.auth.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
@RequiredArgsConstructor
public class TestDataHelperAuth {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    public ResultActions signUpUserRequest(UserDto dto) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
    }

    public ResultActions signInUserRequest(LoginDto dto) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        );
    }


}
