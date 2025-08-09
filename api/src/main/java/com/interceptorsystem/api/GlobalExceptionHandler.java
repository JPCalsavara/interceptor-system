package com.interceptorsystem.api;

import com.interceptorsystem.api.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Este método será chamado sempre que uma `NoSuchElementException` for lançada.
    // Perfeito para erros do tipo "não encontrado".
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDTO> handleNoSuchElementException(NoSuchElementException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                ex.getMessage(), // A mensagem que definimos no UseCase (ex: "Condomínio não encontrado...")
                HttpStatus.NOT_FOUND.value(), // Código de status 404
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // (Opcional, mas recomendado) Este método trata erros de validação.
    // Ele será útil quando começarmos a usar anotações como @Valid nos nossos DTOs.

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Lógica para extrair as mensagens de erro de validação...
        String errorMessage = "Dados inválidos na requisição.";
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                errorMessage,
                HttpStatus.BAD_REQUEST.value(), // Código de status 400
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    // Este é o "apanhador" geral. Qualquer outra exceção não tratada cairá aqui.
    // É uma salvaguarda para evitar que erros inesperados quebrem a aplicação.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                "Ocorreu um erro inesperado no servidor.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // Código de status 500
                LocalDateTime.now()
        );
        // Usa o logger para registar o erro e a sua stack trace. Esta é a forma correta.
       System.out.println("Erro não esperado capturado pelo GlobalExceptionHandler");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
