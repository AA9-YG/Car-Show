package com.binaryAA.CarShow.service;

import com.binaryAA.CarShow.entity.Car;
import com.binaryAA.CarShow.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepo;

    @Override
    public List<Car> getCars() {
        return (List<Car>) carRepo.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        Optional<Car> car = carRepo.findById(id);

        if (car.isPresent())
            return car.get();
        else
            throw new IllegalArgumentException("Car not found");
    }
}
