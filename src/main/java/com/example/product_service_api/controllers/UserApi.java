package com.example.product_service_api.controllers;

import com.example.product_service_api.commons.constants.ApiPathConstants;
import com.example.product_service_api.commons.entities.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.USER_ROUTE)
public interface UserApi {
    @GetMapping(value = "/{userId}")
    ResponseEntity<UserModel> getUser(
            @PathVariable Long userId
    );

    @PutMapping(value = "/{userId}")
    ResponseEntity<UserModel> updateUser(
            @PathVariable Long userId,
            @RequestBody UserModel userRequest
    );
    @DeleteMapping(value = "/{userId}")
    ResponseEntity<Void> deleteUser(
            @PathVariable Long userId
    );
}
