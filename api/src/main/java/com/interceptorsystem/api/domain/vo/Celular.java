package com.interceptorsystem.api.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class Celular {

    private String valor;

    public Celular(String valor) {
        if (!isValido(valor)) {
            throw new IllegalArgumentException("Número de celular inválido: " + valor);
        }
        // Armazena apenas os dígitos do número
        this.valor = valor.replaceAll("[^0-9]", "");
    }

    /**
     * Valida um número de celular brasileiro.
     * Aceita formatos como (11) 98765-4321, 11987654321, etc.
     */
    public static boolean isValido(String celular) {
        if (celular == null || celular.isBlank()) {
            return false;
        }

        String celularLimpo = celular.replaceAll("[^0-9]", "");

        // Verifica se o número tem 10 ou 11 dígitos (com ou sem o nono dígito)
        return celularLimpo.length() == 10 || celularLimpo.length() == 11;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Celular celular = (Celular) o;
        return Objects.equals(valor, celular.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
