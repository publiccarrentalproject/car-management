package com.carservice.backend.rest;

import com.carservice.backend.exception.CarException;
import com.carservice.backend.model.Car;
import com.carservice.backend.service.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/rest")
public class CarRestController {
    private CarService carService;

    @Autowired
    public CarRestController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/cars")
    public CollectionModel<EntityModel<Car>> getCars(@RequestParam("rentable") boolean rentable) {

        List<EntityModel<Car>> cars = StreamSupport.stream(carService.findByRentable(rentable).spliterator(), false)
                .map(car -> new EntityModel<Car>(car,
                        linkTo(methodOn(CarRestController.class).getCar(car.getPlateNumber())).withSelfRel(),
                        linkTo(methodOn(CarRestController.class).getCars(rentable)).withRel("cars")))
                .collect(Collectors.toList());

        return new CollectionModel<>(
                cars,
                linkTo(methodOn(CarRestController.class).getCars(rentable)).withSelfRel()
                        .andAffordance(afford(methodOn(CarRestController.class).createCar(null))));
    }

    @GetMapping(value = "/car/{plateNumber}")
    public EntityModel<Car> getCar(@PathVariable("plateNumber") String plateNumber) {
        Class<CarRestController> controllerClass = CarRestController.class;
        Link findOneLink = linkTo(methodOn(controllerClass).getCar(plateNumber)).withSelfRel();

        Car car = carService.findByPlateNumber(plateNumber);

        return new EntityModel<Car>(car,
                findOneLink
                .andAffordance(afford(methodOn(controllerClass).updateCar(plateNumber, car)))
                .andAffordance(afford(methodOn(controllerClass).deleteByPlateNumber(plateNumber))));
    }

    @PostMapping(value = "/car")
    public ResponseEntity<URI> createCar(@RequestBody Car car) {
        try {
            carService.save(car);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{plateNumber}")
                    .buildAndExpand(car.getPlateNumber()).toUri();

            return ResponseEntity.created(location).build();

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/car/{plateNumber}")
    public ResponseEntity<?> updateCar(@PathVariable("plateNumber") String plateNumber, @RequestBody Car aCar) {
        try {

            Car car = carService.findByPlateNumber(plateNumber);

            BeanUtils.copyProperties(aCar, car, "id");

            carService.save(car);

            return ResponseEntity.ok().build();

        } catch (CarException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/car/{plateNumber}")
    public ResponseEntity<?> deleteByPlateNumber(@PathVariable("plateNumber") String plateNumber) {
        try {
            carService.findByPlateNumber(plateNumber);

            this.carService.deleteByPlateNumber(plateNumber);

            return ResponseEntity.ok().build();

        } catch (CarException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
