package com.interceptorsystem.api.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String cep) {
        // Validações básicas
        if (logradouro == null || logradouro.isBlank()) {
            throw new IllegalArgumentException("Logradouro não pode ser vazio.");
        }
        if (cidade == null || cidade.isBlank()) {
            throw new IllegalArgumentException("Cidade não pode ser vazia.");
        }
        if (cep == null || !cep.matches("\\d{5}-?\\d{3}")) {
            throw new IllegalArgumentException("CEP inválido.");
        }

        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep.replaceAll("[^0-9]", ""); // Armazena apenas os números
    }

}
