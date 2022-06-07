package com.tennis.back.driver.repository.PlayerAtelierRepository;

public class InvalidFileException extends RuntimeException {
    public InvalidFileException(Exception e) {
        super(e);
    }
}
