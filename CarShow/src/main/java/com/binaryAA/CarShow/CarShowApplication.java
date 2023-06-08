package com.binaryAA.CarShow;

import com.binaryAA.CarShow.entity.Owner;
import com.binaryAA.CarShow.repository.CarRepository;
import com.binaryAA.CarShow.repository.OwnerRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.binaryAA.CarShow.entity.Car;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CarShowApplication implements CommandLineRunner {

	@Autowired // This will provide an implemented object for this interface
	private CarRepository carRepo;

//	private CarRepository carRepo; // Another way for dependency injection (Constructor injection)
//
//	public CarShowApplication(CarRepository carRepo) {
//		this.carRepo = carRepo;
//	}

	@Autowired
	private OwnerRepository ownerRepo;

	private static final Logger logger = LoggerFactory.getLogger(CarShowApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CarShowApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Owner owner1 = new Owner("John", "Doe");
		Owner owner2 = new Owner("Bob", "Olson");

		List<Car> cars = Arrays.asList(
				new Car("Ford", "Mustang", "Red", "ADF_1121", 2021, 49000, owner1),
				new Car("Ford", "Mustang", "Red", "ADF_1121", 2021, 49000, owner2)
		);

		List<Owner> owners = Arrays.asList(owner1, owner2);
		// OR
		//ownerRepo.save(owner1);
		//ownerRepo.save(owner2);

		carRepo.saveAll(cars);
		ownerRepo.saveAll(owners);

		carRepo.findAll().forEach(car -> logger.info(car.getMake() + " " + car.getModel() + " " + car.getColor()));
	}
}
