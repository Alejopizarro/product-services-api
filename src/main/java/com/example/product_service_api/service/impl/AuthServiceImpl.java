package com.example.product_service_api.service.impl;

import com.example.product_service_api.commons.dtos.LoginRequest;
import com.example.product_service_api.commons.dtos.TokenResponse;
import com.example.product_service_api.commons.dtos.UserRequest;
import com.example.product_service_api.commons.entities.UserModel;
import com.example.product_service_api.repositories.UserRepository;
import com.example.product_service_api.service.AuthService;
import com.example.product_service_api.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    @Override
    public TokenResponse createUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(userRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getUserId()))
                .orElseThrow(() -> new RuntimeException("Error creating user"));
    }

    @Override
    public TokenResponse loginUser(LoginRequest loginRequest) {
        return Optional.of(loginRequest)
                .map(this::findUserByEmail)
                .filter(user -> validatePassword(loginRequest.getPassword(), user.getPassword()))
                .map(user -> jwtService.generateToken(user.getUserId()))
                .orElseThrow(() -> new RuntimeException("Invalid login credentials"));
    }

    private boolean validatePassword(String password, String encodedPassword) {
        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new RuntimeException("Invalid credentials");
        }
        return true;
    }

    private UserModel findUserByEmail(LoginRequest loginRequest) {
        return userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private UserModel mapToEntity(UserRequest userRequest) {
        return UserModel.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .name(userRequest.getName())
                .build();

    }
}
