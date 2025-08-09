package com.interceptorsystem.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando se tenta criar um condomínio com um CNPJ que já existe.
 * A anotação @ResponseStatus faz com que o Spring retorne o código HTTP 409 (Conflict)
 * automaticamente caso esta exceção não seja tratada por um @ExceptionHandler.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class CondominioJaExisteException extends RuntimeException {
    public CondominioJaExisteException(String message) {
        super(message);
    }
}