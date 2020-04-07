package com.carservice.backend.service;

import java.util.List;

import com.carservice.backend.model.Car;

public interface CarService {

    List<Car> findByRentable(boolean isRentable);

    Car findByPlateNumber(String plateNumber);

    void deleteByPlateNumber(String plateNumber);

    void save(Car car);
}
