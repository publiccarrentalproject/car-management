package com.carservice.backend.rest;

import com.carservice.backend.exception.CarException;
import com.carservice.backend.model.Car;
import com.carservice.backend.service.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.URI;
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
    public ResponseEntity<?> createCar(@RequestBody Car car) {
        Car savedCar = carService.save(car);

        return new EntityModel<Car>(savedCar,
                linkTo(methodOn(CarRestController.class).getCar(savedCar.getPlateNumber())).withSelfRel()
                        .andAffordance(afford(methodOn(CarRestController.class).updateCar(savedCar.getPlateNumber(), savedCar)))
                        .andAffordance(afford(methodOn(CarRestController.class).deleteByPlateNumber(savedCar.getPlateNumber()))),
                linkTo(methodOn(CarRestController.class).getCars(true)).withRel("cars")).getLink(IanaLinkRelations.SELF)
                .map(Link::getHref)
                .map(href -> {
                    try {
                        return new URI(href);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(uri -> ResponseEntity.noContent().location(uri).build())
                .orElse(ResponseEntity.badRequest().body("Unable to create " + car));
    }

    @PutMapping(value = "/car/{plateNumber}")
    public ResponseEntity<?> updateCar(@PathVariable("plateNumber") String plateNumber, @RequestBody Car aCar) {
        Car car = carService.findByPlateNumber(plateNumber);

        BeanUtils.copyProperties(aCar, car, "id");

        Car updatedCar = carService.save(car);

        return new EntityModel<>(updatedCar,
                linkTo(methodOn(CarRestController.class).getCar(updatedCar.getId())).withSelfRel()
                        .andAffordance(afford(methodOn(CarRestController.class).updateCar(updatedCar.getPlateNumber(), updatedCar)))
                        .andAffordance(afford(methodOn(CarRestController.class).deleteByPlateNumber(updatedCar.getPlateNumber()))),
                linkTo(methodOn(CarRestController.class).getCars(true)).withRel("cars")).getLink(IanaLinkRelations.SELF)
                .map(Link::getHref).map(href -> {
                    try {
                        return new URI(href);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(uri -> ResponseEntity.noContent().location(uri).build()) //
                .orElse(ResponseEntity.badRequest().body("Unable to update " + car));
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
