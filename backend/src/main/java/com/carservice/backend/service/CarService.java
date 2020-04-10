package com.carservice.backend.service;

import java.util.List;

import com.carservice.backend.model.Car;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface CarService {

    List<Car> findByRentable(boolean isRentable);

    Car findByPlateNumber(String plateNumber);

    void deleteByPlateNumber(String plateNumber);

    Car save(Car car);

    ResponseEntity<?> errorMap(BindingResult result);
}
