package com.carservice.backend.service;

import com.carservice.backend.exception.CarAlreadyExistsException;
import com.carservice.backend.exception.CarNotFoundException;
import com.carservice.backend.model.Car;
import com.carservice.backend.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
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
        if (car == null) {
            throw new CarNotFoundException("Car not found with plate number :" + plateNumber);
        }
        return car;
    }

    @Override
    public void deleteByPlateNumber(String plateNumber) {
        carRepository.deleteByPlateNumber(plateNumber);
    }

    @Override
    public Car save(Car car) {
        Car aCar = carRepository.findByPlateNumber(car.getPlateNumber());
        if (aCar != null) {
            throw new CarAlreadyExistsException("Car with plate number " + car.getPlateNumber() + " already exists!");
        }

        return carRepository.save(car);
    }

    @Override
    public Car update(String plateNumber, Car car) {
        // Check whether the plate number is changed or not
        if (plateNumber.equalsIgnoreCase(car.getPlateNumber()) == false) {
            Car aCar = carRepository.findByPlateNumber(car.getPlateNumber());
            if (aCar != null) {
                throw new CarAlreadyExistsException("Car with plate number " + car.getPlateNumber() + "  already exists!");
            }
        }

        return carRepository.save(car);
    }

    public ResponseEntity<?> errorMap(BindingResult result) {

        var errorM = new HashMap<>();

        for (FieldError error : result.getFieldErrors()) {
            errorM.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(errorM, HttpStatus.BAD_REQUEST);

    }
}
