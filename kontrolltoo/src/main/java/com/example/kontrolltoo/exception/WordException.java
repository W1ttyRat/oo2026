package com.example.kontrolltoo.exception;

public class WordException extends RuntimeException {

    public WordException(String message) {
        super(message);
    }

    public static void validateWord(String word) {
        if (word == null || word.trim().isEmpty()) {
            throw new WordException("Word cannot be empty");
        }

        // Check if word contains only numbers or symbols (no letters)
        if (!word.matches(".*[a-zA-Z].*")) {
            throw new WordException("Word must contain at least one letter");
        }
    }
}