package com.carservice.backend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carservice.backend.model.Car;
import com.carservice.backend.repository.CarRepository;

@RestController
@RequestMapping("/cars")
public class CarRestController {
	@Autowired
	private CarRepository carRepository;

	@GetMapping(value = "/")
	public ResponseEntity<List<Car>> getCars(@RequestParam("rentable") boolean rentable) {
		List<Car> result = carRepository.findByRentable(rentable);
		
		return ResponseEntity.ok(result);
	}

//	@PostMapping(value = "/car")
//	public ResponseEntity<URI> createCar(@RequestBody Car car) {
//		carRepository.save(car);
//		
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{plateNumber}").buildAndExpand(car.getPlateNumber()).toUri();
//		
//		return ResponseEntity.created(location).build();
//	}

}
