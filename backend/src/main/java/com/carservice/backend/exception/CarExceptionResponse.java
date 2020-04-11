package com.carservice.backend.exception;


public class CarExceptionResponse {
    private String carNotFound;

    public CarExceptionResponse(String message) {
       this.carNotFound = message;
    }
}
