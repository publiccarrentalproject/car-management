package com.carservice.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Document("Car")
public class Car {
	@Id
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
