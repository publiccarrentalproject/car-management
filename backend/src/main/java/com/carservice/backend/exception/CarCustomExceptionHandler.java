package com.carservice.backend.exception;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ConditionalOnProperty (name = "car.errors.restcontrolleradvice", havingValue = "true")
public class CarCustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(CarNotFoundException.class)
    public final ResponseEntity<CarExceptionResponse> handleCarNotFoundException(CarNotFoundException ex) {
        CarExceptionResponse response = new CarExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(CarAlreadyExistsException.class)
    public final ResponseEntity<CarExceptionResponse> handleCarAlreadyExistsException(CarAlreadyExistsException ex) {
        CarExceptionResponse response = new CarExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<CarExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        CarExceptionResponse response = new CarExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
