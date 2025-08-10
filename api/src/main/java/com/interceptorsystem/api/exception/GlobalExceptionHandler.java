package com.interceptorsystem.api.exception;

import com.interceptorsystem.api.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata o erro para quando um condomínio que se tenta criar já existe.
     * Retorna HTTP 409 (Conflict).
     */
    @ExceptionHandler(CondominioJaExisteException.class)
    public ResponseEntity<ErrorResponseDTO> handleCondominioJaExisteException(CondominioJaExisteException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Trata o erro para quando um condomínio não é encontrado no banco de dados.
     * Retorna HTTP 404 (Not Found).
     */
    @ExceptionHandler(CondominioNaoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> handleCondominioNaoEncontradoException(CondominioNaoEncontradoException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Trata erros de validação dos DTOs (ex: campos nulos, formatos inválidos).
     * Retorna HTTP 400 (Bad Request).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = "Dados inválidos na requisição."; // Pode ser melhorado para listar os campos com erro.
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                errorMessage,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Captura qualquer outra exceção não tratada para evitar que a aplicação quebre.
     * Retorna HTTP 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                "Ocorreu um erro inesperado no servidor.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );
        // Em um ambiente de produção, use um logger (ex: SLF4J) para registrar a exceção completa.
        System.err.println("Erro não esperado capturado: " + ex.getMessage());
        ex.printStackTrace(); // Útil para debug.
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
