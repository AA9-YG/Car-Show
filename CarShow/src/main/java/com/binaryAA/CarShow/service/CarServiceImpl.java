package com.binaryAA.CarShow.service;

import com.binaryAA.CarShow.entity.Car;
import com.binaryAA.CarShow.exception.ResourceNotFoundException;
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
        Car car = carRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find car with id"));

        return car;

//        Optional<Car> car = carRepo.findById(id);
//
//        if (car.isPresent())
//            return car.get();
//        else
//            throw new ResourceNotFoundException("Car not found");
    }

    @Override
    public Car saveCar(Car car) {
        return carRepo.save(car);
    }

    @Override
    public Car updateCar(Long id, Car car) {
        Car existingCar = carRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find car with id"));
        existingCar.setMake(car.getMake());
        existingCar.setModel(car.getModel());
        existingCar.setColor(car.getColor());
        existingCar.setPrice(car.getPrice());
        existingCar.setYear(car.getYear());
        existingCar.setRegisterNumber(car.getRegisterNumber());

        return carRepo.save(existingCar);
    }

    @Override
    public Car deleteCar(Long id) {
        Car existingCar = carRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find car with id"));
        carRepo.delete(existingCar);
        return existingCar;
    }


}
