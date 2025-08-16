package com.interceptorsystem.api.dto;

// Supondo que você tenha um enum para o status
import com.interceptorsystem.api.domain.enums.StatusContrato;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ContratoRequestDTO(
        String descricao,
        BigDecimal valorTotal,
        BigDecimal valorDiaria,
        LocalDate dataInicio,
        LocalDate dataFim,
        StatusContrato statusContrato,
        UUID condominioId // ID do condomínio ao qual este contrato pertence
) {}
