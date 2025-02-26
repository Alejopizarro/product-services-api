package com.example.product_service_api.controllers.impl;

import com.example.product_service_api.commons.entities.UserModel;
import com.example.product_service_api.controllers.UserApi;
import com.example.product_service_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserModel> getUser(Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @Override
    public ResponseEntity<UserModel> updateUser(Long userId, UserModel userRequest) {
        return ResponseEntity.ok(userService.updateUser(userId, userRequest));
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
