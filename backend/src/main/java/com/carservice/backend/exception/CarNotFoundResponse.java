package com.carservice.backend.exception;


public class CarNotFoundResponse {
    private String carNotFound;

    public CarNotFoundResponse(String message) {
       this.carNotFound = message;
    }
}
