package com.carservice.backend.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carservice.backend.model.Car;
import com.carservice.backend.repository.CarRepository;

@RestController
@RequestMapping("/rest")
public class CarRestController {
	@Autowired
	private CarRepository carRepository;

	@GetMapping(value = "/cars")
	public ResponseEntity<List<Car>> getCars(@RequestParam("rentable") boolean rentable) {
		List<Car> result = carRepository.findByRentable(rentable);

		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "/car/{plateNumber}")
	public ResponseEntity<Car> getCars(@PathVariable("plateNumber") String plateNumber) {
		Car result = carRepository.findByPlateNumber(plateNumber);

		return ResponseEntity.ok(result);
	}
	

	@PostMapping(value = "/car")
	public ResponseEntity<URI> createCar(@RequestBody Car car) {
		try {
			carRepository.save(car);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{plateNumber}")
					.buildAndExpand(car.getPlateNumber()).toUri();

			return ResponseEntity.created(location).build();

		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
    @DeleteMapping("/car/{plateNumber}")
    public void deleteByPlateNumber(@PathVariable("plateNumber") String plateNumber){
        this.carRepository.deleteByPlateNumber(plateNumber);
    }


}
