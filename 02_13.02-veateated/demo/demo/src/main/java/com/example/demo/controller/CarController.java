package com.example.demo.controller;

import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @GetMapping("cars")
    public List<Car> getCars() {
        return carRepository.findAll(); // leiab kõik autod
    }

    @DeleteMapping("cars/{id}")
    public List<Car> deleteCars(@PathVariable Long id) {
        carRepository.deleteById(id); // kustutab auto, id järgi
        return carRepository.findAll();
    }

    @PostMapping("cars")
    public List<Car> saveCars(@RequestBody Car car) {
        carService.validate(car); // valideerimis
        carRepository.save(car); // salvestab
        return carRepository.findAll(); // uuenenud list
    }

    @PutMapping("cars")
    public List<Car> updateCars(@RequestBody Car car) {
        if (car.getId() == null) {
            throw new RuntimeException("car id is null");
        }
        if (!carRepository.existsById(car.getId())) {
            throw new RuntimeException("car does not exist");
        }
        carRepository.save(car);
        return carRepository.findAll();
    }
}