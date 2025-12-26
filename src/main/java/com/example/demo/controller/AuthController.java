package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AuthLoginRequest;
import com.example.demo.dto.AuthRegisterRequest;
import com.example.demo.dto.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthLoginRequest request) {

        return new AuthResponse(
                "dummy-jwt-token",
                1L,
                request.getUsernameOrEmail(),
                "USER"
        );
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRegisterRequest request) {

        String role = request.getRole() != null ? request.getRole() : "USER";

        return new AuthResponse(
                "dummy-jwt-token",
                1L,
                request.getEmail(),
                role
        );
    }
}
