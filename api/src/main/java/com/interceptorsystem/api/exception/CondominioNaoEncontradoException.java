package com.interceptorsystem.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CondominioNaoEncontradoException extends RuntimeException {
    public CondominioNaoEncontradoException(String message) {
        super(message);
    }
}

