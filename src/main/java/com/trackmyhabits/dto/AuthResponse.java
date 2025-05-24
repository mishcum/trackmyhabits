package com.trackmyhabits.dto;

public record AuthResponse(String token) {
    public AuthResponse {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("Token cannot be null or blank");
        }
    }
}
