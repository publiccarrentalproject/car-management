package com.carservice.backend.rest;

import com.carservice.backend.model.Car;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CarRestControllerTest {
    private RestTemplate restTemplate;

    @Autowired
    private DataProvider dataProvider;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        restTemplate = new RestTemplate();
        dataProvider.createUsers();
    }

    @AfterEach
    public void cleanUp() {
        dataProvider.deleteUsers();
    }

    @Test
    public void testCreateOwner() {
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
    public void testGetCarByPlateNumber() {
        ResponseEntity<Car> response = restTemplate.getForEntity(createURLWithPort("/rest/car/EE-4321-EE"), Car.class);

        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
        MatcherAssert.assertThat(response.getBody().getPlateNumber(), Matchers.equalTo("EE-4321-EE"));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
