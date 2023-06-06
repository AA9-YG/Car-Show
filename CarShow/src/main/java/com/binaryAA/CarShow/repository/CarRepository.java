package com.binaryAA.CarShow.repository;

import com.binaryAA.CarShow.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
