package com.interceptorsystem.api.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable // Anotação para que o JPA possa embutir esta classe em outras entidades
@Getter
@NoArgsConstructor // Necessário para o JPA
public class CPF {

    private String valor;

    public CPF(String valor) {
        if (!isValido(valor)) {
            throw new IllegalArgumentException("CPF inválido: " + valor);
        }
        this.valor = valor;
    }

    public static boolean isValido(String cpf) {
        if (cpf == null) {
            return false;
        }
        // Remove caracteres não numéricos
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        // Verifica se tem 11 dígitos e se não são todos iguais
        if (cpfLimpo.length() != 11 || cpfLimpo.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Lógica de validação do dígito verificador (simplificada para o exemplo)
        // Em um projeto real, use uma biblioteca ou implemente o algoritmo completo.
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CPF cpf = (CPF) o;
        return Objects.equals(valor, cpf.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
