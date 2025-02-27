package com.example.product_service_api.controllers.impl;

import com.example.product_service_api.commons.dtos.TokenResponse;
import com.example.product_service_api.commons.dtos.UserRequest;
import com.example.product_service_api.controllers.AuthApi;
import com.example.product_service_api.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<TokenResponse> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.createUser(userRequest));
    }

    @Override
    public ResponseEntity<TokenResponse> loginUser(UserRequest userRequest) {
        return ResponseEntity.ok(authService.loginUser(userRequest));
    }
}
