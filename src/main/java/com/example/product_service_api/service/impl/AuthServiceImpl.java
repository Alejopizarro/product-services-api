package com.example.product_service_api.service.impl;

import com.example.product_service_api.commons.dtos.TokenResponse;
import com.example.product_service_api.commons.dtos.UserRequest;
import com.example.product_service_api.commons.entities.UserModel;
import com.example.product_service_api.commons.exceptions.BadRequestException;
import com.example.product_service_api.mappers.UserMapper;
import com.example.product_service_api.repositories.UserRepository;
import com.example.product_service_api.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }


    @Override
    public TokenResponse createUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(userMapper::mapToEntity)
                .map(this::encodePassword)
                .map(userRepository::save)
                .map(user -> jwtService.generateToken(user.getUserId()))
                .orElseThrow(() -> new BadRequestException("Error creating user"));
    }

    @Override
    public TokenResponse loginUser(UserRequest userRequest) {
        return userRepository.findByEmail(userRequest.getEmail())
                .filter(user -> passwordEncoder.matches(userRequest.getPassword(), user.getPassword()))
                .map(user -> jwtService.generateToken(user.getUserId()))
                .orElseThrow(() -> new BadRequestException("Invalid credentials"));
    }


    private UserModel encodePassword(UserModel user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}