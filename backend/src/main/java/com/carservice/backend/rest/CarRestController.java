package com.carservice.backend.rest;

import com.carservice.backend.model.Car;
import com.carservice.backend.service.CarService;
import com.carservice.backend.exception.CarNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class CarRestController {
    @Autowired
    private CarService carService;

    @GetMapping(value = "/cars")
    public ResponseEntity<List<Car>> getCars(@RequestParam("rentable") boolean rentable) {
        List<Car> result = carService.findByRentable(rentable);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/car/{plateNumber}")
    public ResponseEntity<Car> getCars(@PathVariable("plateNumber") String plateNumber) {
        try {
            Car car = carService.findByPlateNumber(plateNumber);
            return ResponseEntity.ok(car);
        } catch (CarNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
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
    public ResponseEntity<?> updateOwner(@PathVariable("plateNumber") String plateNumber, @RequestBody Car aCar) {
        try {

            Car car = carService.findByPlateNumber(plateNumber);

            BeanUtils.copyProperties(aCar, car, "id");

            carService.save(car);

            return ResponseEntity.ok().build();

        } catch (CarNotFoundException ex) {
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

        } catch (CarNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

}
