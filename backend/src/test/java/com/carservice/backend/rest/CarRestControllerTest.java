package com.carservice.backend.rest;

import com.carservice.backend.model.Car;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class CarRestControllerTest {
    private RestTemplate restTemplate;

    @Autowired
    private DataProvider dataProvider;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        restTemplate = new RestTemplate();
        dataProvider.createCars();
    }

    @AfterEach
    public void cleanUp() {
        dataProvider.deleteCars();
    }


    @Test
    public void tesGetAllCars() {
        ResponseEntity<List> response = restTemplate.getForEntity(createURLWithPort("/rest/cars?rentable=true"), List.class);
        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

        List<Map<String, String>> body = response.getBody();
        List<String> models = body.stream().map(e -> e.get("model")).collect(Collectors.toList());
        MatcherAssert.assertThat(models, Matchers.containsInAnyOrder("Corolla", "Yaris"));
    }

    @Test
    public void testCreateCar() {
        Car car = new Car();
        car.setPlateNumber("DD-4321-DD");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setYear(2011);
        car.setEngine("D-4D");
        car.setFuelType("Diesel");
        car.setFuelConsumption(6);
        car.setNumberOfDoors(4);
        car.setNumberOfSeats(5);
        car.setRentable(true);

        RestTemplate restTemplate = new RestTemplate();
        URI location = restTemplate.postForLocation(createURLWithPort("/rest/car"), car);

        Car carSaved = restTemplate.getForObject(location, Car.class);

        MatcherAssert.assertThat(carSaved.getPlateNumber(), Matchers.equalTo(car.getPlateNumber()));
        MatcherAssert.assertThat(carSaved.getBrand(), Matchers.equalTo(car.getBrand()));
    }

    @Test
    public void testCarAlreadyExists() {
        Car car = new Car();
        car.setPlateNumber("DD-4321-DD");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setYear(2011);
        car.setEngine("D-4D");
        car.setFuelType("Diesel");
        car.setFuelConsumption(6);
        car.setNumberOfDoors(4);
        car.setNumberOfSeats(5);
        car.setRentable(true);

        RestTemplate restTemplate = new RestTemplate();

        assertThatThrownBy(() -> {
            restTemplate.postForLocation(createURLWithPort("/rest/car"), car);
            restTemplate.postForLocation(createURLWithPort("/rest/car"), car);
        }).isInstanceOf(HttpClientErrorException.class)
                .hasMessageContaining("Car with plate number DD-4321-DD already exists!");
    }

    @Test
    public void testCreateCarInvalidData() {
        Car car = new Car();
        car.setPlateNumber("DD-4321-DD");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setYear(-1);
        car.setEngine("D-4D");
        car.setFuelType("Diesel");
        car.setFuelConsumption(-1);
        car.setNumberOfDoors(-1);
        car.setNumberOfSeats(-1);
        car.setRentable(true);

        RestTemplate restTemplate = new RestTemplate();

        assertThatThrownBy(() -> {
            restTemplate.postForLocation(createURLWithPort("/rest/car"), car);
        });
    }

    @Test
    public void testGetCarByPlateNumber() {
        ResponseEntity<Car> response = restTemplate.getForEntity(createURLWithPort("/rest/car/EE-4321-EE"), Car.class);

        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        MatcherAssert.assertThat(response.getBody().getPlateNumber(), Matchers.equalTo("EE-4321-EE"));
    }

    @Test
    public void testCarNotFound() {
        assertThatThrownBy(() -> {
            restTemplate.getForEntity(createURLWithPort("/rest/car/XX-4321-XX"), Car.class);
        }).isInstanceOf(HttpClientErrorException.class)
                .hasMessageContaining("Car not found");

    }

    @Test
    public void testUpdateCar() {
        RestTemplate restTemplate = new RestTemplate();
        Car car = restTemplate.getForObject(createURLWithPort("/rest/car/EE-4321-EE"), Car.class);
        car.setModel("Auris");
        car.setYear(2012);
        car.setEngine("1.4D");

        restTemplate.put(createURLWithPort("/rest/car/EE-4321-EE"), car);
        Car carUpdated = restTemplate.getForObject(createURLWithPort("/rest/car/EE-4321-EE"), Car.class);

        MatcherAssert.assertThat(carUpdated.getModel(), Matchers.equalTo(car.getModel()));
        MatcherAssert.assertThat(carUpdated.getYear(), Matchers.equalTo(car.getYear()));
        MatcherAssert.assertThat(carUpdated.getEngine(), Matchers.equalTo(car.getEngine()));
    }

    @Test
    public void testUpdatePlateNumberWithExistingOne() {
        RestTemplate restTemplate = new RestTemplate();
        Car car = restTemplate.getForObject(createURLWithPort("/rest/car/EE-4321-EE"), Car.class);
        car.setPlateNumber("WW-9876-WW");

        assertThatThrownBy(() -> {
            restTemplate.put(createURLWithPort("/rest/car/EE-4321-EE"), car);
        }).isInstanceOf(HttpClientErrorException.class)
                .hasMessageContaining("Car with plate number WW-9876-WW already exists!");
    }

    @Test
    public void testDeleteCar() {
        restTemplate.delete(createURLWithPort("/rest/car/EE-4321-EE"));

        assertThatThrownBy(() -> {
            ResponseEntity<Car> response = restTemplate.getForEntity(createURLWithPort("/rest/car/EE-4321-EE"), Car.class);
        }).isInstanceOf(HttpClientErrorException.class)
                .hasMessageContaining("Car not found");

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
