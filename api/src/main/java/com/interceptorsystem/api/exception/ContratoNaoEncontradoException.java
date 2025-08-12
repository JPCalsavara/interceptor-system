package com.interceptorsystem.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando uma operação tenta acessar um contrato
 * que não existe no banco de dados.
 * A anotação @ResponseStatus instrui o Spring a retornar HTTP 404 (Not Found).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContratoNaoEncontradoException extends RuntimeException {
    public ContratoNaoEncontradoException(String message) {
        super(message);
    }
}
