package com.microservices.auth.service;

import com.microservices.auth.dto.LoginRequest;
import com.microservices.auth.dto.LogoutRequest;
import com.microservices.auth.dto.RefreshTokenRequest;
import com.microservices.auth.dto.RegisterRequest;
import com.microservices.auth.dto.TokenResponse;

public interface AuthService {
    TokenResponse login(LoginRequest request);
    TokenResponse register(RegisterRequest request);
    TokenResponse refreshToken(RefreshTokenRequest request);
    void logout(LogoutRequest request);
}
