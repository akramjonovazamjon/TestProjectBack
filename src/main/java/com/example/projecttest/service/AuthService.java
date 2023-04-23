package com.example.projecttest.service;

import com.example.projecttest.dto.auth.LoginDto;
import com.example.projecttest.dto.auth.UserDto;
import com.example.projecttest.dto.auth.TokenResult;
import com.example.projecttest.entity.User;
import com.example.projecttest.exception.user.UserExistByUsernameException;
import com.example.projecttest.repository.UserRepository;
import com.example.projecttest.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    @Value("${jwt.secret}")
    private String tokenSecret;

    public TokenResult signUp(UserDto dto) {

        if (userRepository.existsByUsername(dto.username()))
            throw new UserExistByUsernameException(dto.username());

        User user = User.of(dto.name(), dto.username(), passwordEncoder.encode(dto.password()));
        User savedUser = userRepository.save(user);
        String token = JwtUtils.generateToken(savedUser.asDetailedUser(), tokenSecret);

        return new TokenResult(token);
    }


    public TokenResult signIn(LoginDto dto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));
        User user = userRepository.findByUsername(dto.username()).orElseThrow();
        String token = JwtUtils.generateToken(user.asDetailedUser(), tokenSecret);
        return new TokenResult(token);
    }

}
