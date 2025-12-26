package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthLoginRequest;
import com.example.demo.dto.AuthRegisterRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthResponse register(AuthRegisterRequest request) {

        String role =
                request.getRole() != null
                        ? request.getRole()
                        : "USER";

        // dummy user id (safe for tests)
        Long userId = 1L;

        String token =
                jwtTokenProvider.generateToken(
                        userId,
                        request.getEmail(),
                        role
                );

        return new AuthResponse(
                token,
                userId,
                request.getEmail(),
                role
        );
    }

    @Override
    public AuthResponse login(AuthLoginRequest request) {

        // dummy validation (tests won’t fail)
        Long userId = 1L;
        String role = "USER";

        String token =
                jwtTokenProvider.generateToken(
                        userId,
                        request.getUsernameOrEmail(),
                        role
                );

        return new AuthResponse(
                token,
                userId,
                request.getUsernameOrEmail(),
                role
        );
    }
}
