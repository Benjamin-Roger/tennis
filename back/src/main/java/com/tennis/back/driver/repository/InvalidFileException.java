package com.tennis.back.driver.repository;

public class InvalidFileException extends RuntimeException {
    public InvalidFileException(Exception e) {
        super(e);
    }
}
