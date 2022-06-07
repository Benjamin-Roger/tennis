package com.tennis.back.domain.useCase;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.NOT_FOUND,
        reason = "Player not found")
public class NoPlayerFoundException extends RuntimeException {
    public NoPlayerFoundException(String e) {
        super(e);
    }
}
