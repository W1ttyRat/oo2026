package com.example.demo.service;

import com.example.demo.entity.Car;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class CarService {

    private final Pattern vinPattern = Pattern.compile("^[A-HJ-NPR-Z0-9]{17}$");
    private final Pattern licensePlatePattern = Pattern.compile("^[A-Z0-9]{2,10}$");

    public void validate(Car car) {

        // ID validation
        if (car.getId() != null) {
            throw new RuntimeException("cannot create car with ID");
        }

        // Make validation
        if (car.getMake() == null) {
            throw new RuntimeException("cannot create car without make");
        }


        // Model validation
        if (car.getModel() == null || car.getModel().trim().isEmpty()) {
            throw new RuntimeException("car model cannot be empty");
        }

        // Year validation
        if (car.getYear() == null) {
            throw new RuntimeException("car year cannot be empty");
        }
        if (car.getYear() < 1886 || car.getYear() > 2028) {
            throw new RuntimeException("invalid car year");
        }

        // VIN validation
        if (car.getVin() == null || car.getVin().trim().isEmpty()) {
            throw new RuntimeException("VIN cannot be empty");

        }
    }
}
