package com.trackmyhabits.service;

import com.trackmyhabits.dto.AuthRequest;
import com.trackmyhabits.dto.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);
}
