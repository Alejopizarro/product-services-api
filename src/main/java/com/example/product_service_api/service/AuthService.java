package com.example.product_service_api.service;

import com.example.product_service_api.commons.dtos.LoginRequest;
import com.example.product_service_api.commons.dtos.TokenResponse;
import com.example.product_service_api.commons.dtos.UserRequest;

public interface AuthService {
    TokenResponse createUser(UserRequest userRequest);
    TokenResponse loginUser(LoginRequest loginRequest);
}
