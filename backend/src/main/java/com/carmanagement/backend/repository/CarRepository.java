package com.carmanagement.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.carmanagement.backend.model.Car;

public interface CarRepository extends MongoRepository<Car, String> {

	List<Car> findByRentable(boolean isRentable);

	Car findByPlateNumber(String id);
}
