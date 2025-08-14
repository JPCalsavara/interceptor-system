package com.interceptorsystem.api.dto;

import com.interceptorsystem.api.domain.enums.StatusFuncionario;
import com.interceptorsystem.api.domain.enums.TipoEscala;
import com.interceptorsystem.api.domain.enums.TipoFuncionario;

public record FuncionarioRequestDTO(
        String nome,
        String cpf, // Recebemos o CPF como String
        String celular, // Recebemos o celular como String
        StatusFuncionario statusFuncionario,
        TipoEscala tipoEscala,
        TipoFuncionario tipoFuncionario
) {}
