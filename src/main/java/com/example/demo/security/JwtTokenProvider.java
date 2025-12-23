package com.example.demo.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    // Dummy in-memory token store 
    private static final Map<String, Map<String, Object>> tokenStore = new HashMap<>();

    public String generateToken(Long userId, String email, String role) {
        String token = "dummy-jwt-token-" + userId;

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("email", email);
        claims.put("role", role);

        tokenStore.put(token, claims);
        return token;
    }

    public String generateToken(String email) {
        return generateToken(1L, email, "USER");
    }

    public boolean validateToken(String token) {
        return tokenStore.containsKey(token);
    }

    public String getEmailFromToken(String token) {
        return (String) tokenStore.get(token).get("email");
    }

    public Long getUserIdFromToken(String token) {
        return (Long) tokenStore.get(token).get("userId");
    }

    public String getRoleFromToken(String token) {
        return (String) tokenStore.get(token).get("role");
    }

    public Map<String, Object> getClaims(String token) {
        return tokenStore.get(token);
    }

    // Backward compatibility
    public String getUsernameFromToken(String token) {
        return getEmailFromToken(token);
    }
}
