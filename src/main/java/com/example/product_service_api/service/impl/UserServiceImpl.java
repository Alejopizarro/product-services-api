package com.example.product_service_api.service.impl;

import com.example.product_service_api.commons.entities.UserModel;
import com.example.product_service_api.repositories.UserRepository;
import com.example.product_service_api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserModel getUser(Long userId) {
        return Optional.of(userId)
                .flatMap(userRepository::findById)
                .orElseThrow(() -> new RuntimeException("Error couldn't find user"));
    }

    @Override
    public UserModel updateUser(Long userId, UserModel userRequest) {
        return Optional.of(userId)
                .map(this::getUser)
                .map(updatedUser -> updateEntity(updatedUser, userRequest))
                .map(userRepository::save)
                .orElseThrow(() -> new RuntimeException("Error couldn't update user"));
    }

    @Override
    public void deleteUser(Long userId) {
        Optional.of(userId)
                .map(this::getUser)
                .ifPresent(userRepository::delete);
    }

    private UserModel updateEntity(UserModel updatedUser, UserModel userRequest) {
        updatedUser.setName(userRequest.getName());
        updatedUser.setEmail(userRequest.getEmail());
        updatedUser.setPassword(userRequest.getPassword());
        updatedUser.setRole(userRequest.getRole());
        return updatedUser;
    }
}
