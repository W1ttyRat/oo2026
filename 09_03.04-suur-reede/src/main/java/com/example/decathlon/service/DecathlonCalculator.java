package com.example.decathlon.service;

import com.example.decathlon.exception.InvalidDisciplineException;

public class DecathlonCalculator {

    public static int calculate(String disciplineName, Double value) {
        if (value == null || value < 0) return 0;
        
        Discipline discipline = Discipline.fromString(disciplineName);
        if (discipline == null) {
            throw new InvalidDisciplineException(disciplineName);
        }

        return discipline.calculatePoints(value);
    }
}
