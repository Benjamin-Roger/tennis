package com.tennis.back.domain.useCase;

public class NoPlayerFoundException extends RuntimeException {

    public NoPlayerFoundException(String e) {
        super(e);
    }
}
