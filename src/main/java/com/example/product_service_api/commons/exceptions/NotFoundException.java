package com.example.product_service_api.commons.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends GeneralException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
