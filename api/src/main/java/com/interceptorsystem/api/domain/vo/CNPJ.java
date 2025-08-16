package com.interceptorsystem.api.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class CNPJ {

    private String valor;

    public CNPJ(String valor) {
        if (!isValido(valor)) {
            throw new IllegalArgumentException("CNPJ inválido: " + valor);
        }
        this.valor = valor;
    }

    public static boolean isValido(String cnpj) {
        if (cnpj == null) {
            return false;
        }
        // Remove caracteres não numéricos
        String cnpjLimpo = cnpj.replaceAll("[^0-9]", "");

        // Verifica se tem 14 dígitos
        if (cnpjLimpo.length() != 14) {
            return false;
        }

        // Lógica de validação do dígito verificador (simplificada)
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CNPJ cnpj = (CNPJ) o;
        return Objects.equals(valor, cnpj.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}
