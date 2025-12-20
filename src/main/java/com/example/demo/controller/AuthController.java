package com.example.demo.controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.AuthLoginRequest;
import com.example.demo.dto.AuthRegisterRequest;
import com.example.demo.dto.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRegisterRequest request) {
        // Dummy success response
        return new AuthResponse(
                "dummy-jwt-token",
                request.getUsername(),
                "User registered successfully"
        );
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthLoginRequest request) {
        // Dummy authentication success
        return new AuthResponse(
                "dummy-jwt-token",
                request.getUsername(),
                "Login successful"
        );
    }
}
