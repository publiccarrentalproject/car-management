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

    public void createCars() {
        Car car1 = new Car();
        car1.setPlateNumber("EE-4321-EE");
        car1.setBrand("Toyota");
        car1.setModel("Corolla");
        car1.setYear(2011);
        car1.setEngine("D-4D");
        car1.setFuelType("Diesel");
        car1.setFuelConsumption(6);
        car1.setNumberOfDoors(4);
        car1.setNumberOfSeats(5);
        car1.setRentable(true);

        carRepository.save(car1);

        Car car2 = new Car();
        car2.setPlateNumber("WW-9876-WW");
        car2.setBrand("Toyota");
        car2.setModel("Yaris");
        car2.setYear(2015);
        car2.setEngine("1.1");
        car2.setFuelType("Gasoline");
        car2.setFuelConsumption(4);
        car2.setNumberOfDoors(4);
        car2.setNumberOfSeats(5);
        car2.setRentable(true);

        carRepository.save(car2);
    }

    public void deleteCars() {
        carRepository.deleteByPlateNumber("EE-4321-EE");
        carRepository.deleteByPlateNumber("WW-9876-WW");
        carRepository.deleteByPlateNumber("DD-4321-DD");
    }
}