package com.carservice.backend.service;

import com.carservice.backend.exception.CarException;
import com.carservice.backend.model.Car;
import com.carservice.backend.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> findByRentable(boolean isRentable) {
        return carRepository.findByRentable(isRentable);
    }

    @Override
    public Car findByPlateNumber(String plateNumber) {
        Car car = carRepository.findByPlateNumber(plateNumber);
        if(car == null) {
            throw new CarException("Car not found with plate number :" + plateNumber);
        }
        return car;
    }

    @Override
    public void deleteByPlateNumber(String plateNumber) {
        carRepository.deleteByPlateNumber(plateNumber);
    }

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }
}
