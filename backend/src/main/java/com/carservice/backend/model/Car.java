package com.carservice.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@Document("Car")
public class Car extends RepresentationModel<Car> {
	@Id
	private String id;
	
	private String plateNumber;

	private String brand;
	private String model;
	private String year;
	private String engine;
	private String fuelType;
	private int fuelConsumption;
	private int numberOfDoors;
	private int numberOfSeats;
	private boolean rentable;

	private Color color;
	private Infotainment[] infotainmentTypes;

}
