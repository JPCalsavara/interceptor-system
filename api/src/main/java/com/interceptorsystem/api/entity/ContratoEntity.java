package com.interceptorsystem.api.entity;

import com.interceptorsystem.api.domain.enums.StatusContrato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Table(name = "contratos")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContratoEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private BigDecimal valorDiariaCobrada;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusContrato status;

    @ManyToOne
    @JoinColumn(name = "condominio_id")
    private CondominioEntity condominio;
}
