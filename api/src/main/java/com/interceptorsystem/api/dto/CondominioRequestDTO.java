package com.interceptorsystem.api.dto;

import com.interceptorsystem.api.domain.enums.StatusCondominio;

public record CondominioRequestDTO(
        String nome,
        String cnpj,
        StatusCondominio status,
        // Campos para o Value Object Endereco
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {}
