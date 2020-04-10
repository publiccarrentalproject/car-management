package com.carservice.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CarAdvice {

    @ResponseBody
    @ExceptionHandler(CarException.class)
    public final ResponseEntity<CarNotFoundResponse> carNotFoundResponseResponseEntity(CarException ex) {
        CarNotFoundResponse response = new CarNotFoundResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
