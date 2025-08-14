package com.interceptorsystem.api.dto;

import java.time.LocalTime;
import java.util.UUID;

public record PostoDeTrabalhoRequestDTO(
        String descricao,
        LocalTime horarioInicio,
        LocalTime horarioFim,
        UUID condominioId
) {}
