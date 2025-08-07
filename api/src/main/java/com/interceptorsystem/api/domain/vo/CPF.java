package com.interceptorsystem.api.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.Value;
import org.springframework.stereotype.Component;

@Embeddable
@Value
public class CPF {
    String cpf;
    // No futuro, podemos adicionar validações aqui no construtor.
}
