package com.interceptorsystem.api.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Table(name = "contrato")
@Entity
@Getter
@Setter
public class Contrato {
    @Id
    @GeneratedValue
    private UUID id;

    private BigDecimal valorDiariaCobrada;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    @ManyToOne
    @JoinColumn(name = "condominio_id")
    private Condominio condominio;
}
