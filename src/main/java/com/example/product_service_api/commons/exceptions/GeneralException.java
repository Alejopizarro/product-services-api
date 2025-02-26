package com.example.product_service_api.commons.exceptions;

import org.springframework.http.HttpStatus;

public class GeneralException extends RuntimeException {
    private final HttpStatus httpStatus;

    public GeneralException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public GeneralException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
