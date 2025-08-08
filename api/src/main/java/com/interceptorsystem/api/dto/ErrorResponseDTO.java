package com.interceptorsystem.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor // Lombok para criar um construtor com todos os argumentos
public class ErrorResponseDTO {
    private final String message;
    private final int status;
    private final
    LocalDateTime timestamp;
}
