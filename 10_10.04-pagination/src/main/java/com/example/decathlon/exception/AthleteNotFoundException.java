package com.example.decathlon.exception;

public class AthleteNotFoundException extends RuntimeException {

    public AthleteNotFoundException(Long id) {
        super("Athlete not found with id: " + id);
    }

    public AthleteNotFoundException(String message) {
        super(message);
    }
}
