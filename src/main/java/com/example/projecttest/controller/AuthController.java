package com.example.projecttest.controller;

import com.example.projecttest.dto.ResponseData;
import com.example.projecttest.dto.auth.LoginDto;
import com.example.projecttest.dto.auth.UserDto;
import com.example.projecttest.dto.auth.TokenResult;
import com.example.projecttest.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public ResponseData<TokenResult> signUp(@RequestBody @Valid UserDto dto) {
        TokenResult signUpResult = authService.signUp(dto);
        return ResponseData.of(signUpResult);
    }

    @PostMapping("/sign-in")
    public ResponseData<TokenResult> signIn(@RequestBody @Valid LoginDto dto) {
        TokenResult tokenResult = authService.signIn(dto);
        return ResponseData.of(tokenResult);
    }

}
