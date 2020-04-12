package com.carservice.backend.exception;

import lombok.Data;

@Data
public class CarExceptionResponse {
    private String message;

    public CarExceptionResponse(String message) {
       this.message = message;
    }
}
