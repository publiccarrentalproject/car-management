package com.carservice.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@Document("Car")
public class Car {
    @Id
    private String id;

    @NotBlank(message = "Plate Number cannot be empty!")
    private String plateNumber;

    @NotBlank(message = "Brand cannot be empty!")
    private String brand;

    @NotBlank(message = "Model cannot be empty!")
    private String model;

    @NotBlank(message = "Year cannot be empty!")
    private String year;

    @NotBlank(message = "Engine cannot be empty!")
    private String engine;

    @NotBlank(message = "Fuel type cannot be empty!")
    private String fuelType;

    @Min(value = 0, message = "Fuel consumption {javax.validation.constraints.Min.message}")
    private int fuelConsumption;

    @Min(value = 1, message = "Number of doors {javax.validation.constraints.Min.message}")
    private int numberOfDoors;

    @Min(value = 1, message = "Number of seats {javax.validation.constraints.Min.message}")
    private int numberOfSeats;

    private Color color;

    private boolean rentable;
    private Infotainment[] infotainmentTypes;
}
