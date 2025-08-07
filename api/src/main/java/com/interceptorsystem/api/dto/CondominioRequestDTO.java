package com.interceptorsystem.api.dto;

import com.interceptorsystem.api.domain.enums.StatusCondominio;

public record CondominioRequestDTO(String nome, String cnpj, StatusCondominio status) {
}
