package com.carservice.backend.rest;

import com.carservice.backend.model.Car;
import com.carservice.backend.repository.CarRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@NoArgsConstructor
public class DataProvider {
    private CarRepository carRepository;

    @Autowired
    public DataProvider(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void createUsers() {
        Car car = new Car();
        car.setPlateNumber("EE-4321-EE");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setYear(2011);
        car.setEngine("D-4D");
        car.setFuelType("Diesel");
        car.setFuelConsumption(6);
        car.setNumberOfDoors(4);
        car.setNumberOfSeats(5);
        car.setRentable(true);

        carRepository.save(car);
    }

    public void deleteUsers() {
        carRepository.deleteByPlateNumber("EE-4321-EE");
        carRepository.deleteByPlateNumber("DD-4321-DD");
    }
}