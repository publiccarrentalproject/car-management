package com.carmanagement.backend.model;

import org.springframework.data.annotation.Id;

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
	private Color color;

	private Infotainment[] infotainmentTypes;

}
