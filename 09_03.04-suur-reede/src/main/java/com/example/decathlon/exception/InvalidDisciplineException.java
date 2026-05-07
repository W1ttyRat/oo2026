package com.example.decathlon.exception;

public class InvalidDisciplineException extends RuntimeException {
    public InvalidDisciplineException(String discipline) {
        super("Invalid discipline: " + discipline);
    }
}
