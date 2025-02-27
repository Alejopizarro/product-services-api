package com.example.product_service_api.mappers;

import com.example.product_service_api.commons.dtos.UserRequest;
import com.example.product_service_api.commons.entities.UserModel;
import org.springframework.stereotype.Component;

import static com.example.product_service_api.commons.enums.UserRoleEnum.USER;

@Component
public class UserMapper {
    public UserModel mapToEntity(UserRequest userRequest) {
        return UserModel.builder()
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .role(USER)
                .build();
    }
}
