package com.binaryAA.CarShow.service;

import com.binaryAA.CarShow.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CarService {
    List<Car> getCars();

    Car getCarById(Long id);
}
