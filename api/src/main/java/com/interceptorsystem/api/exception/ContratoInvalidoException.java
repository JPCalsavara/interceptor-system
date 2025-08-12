package com.interceptorsystem.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando os dados fornecidos para criar ou atualizar
 * um contrato são inválidos de acordo com as regras de negócio.
 * A anotação @ResponseStatus instrui o Spring a retornar HTTP 400 (Bad Request).
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContratoInvalidoException extends RuntimeException {
    public ContratoInvalidoException(String message) {
        super(message);
    }
}
