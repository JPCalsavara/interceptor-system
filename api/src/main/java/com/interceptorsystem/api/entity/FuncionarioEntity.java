package com.interceptorsystem.api.entity;

import com.interceptorsystem.api.domain.enums.StatusFuncionario;
import com.interceptorsystem.api.domain.enums.TipoEscala;
import com.interceptorsystem.api.domain.enums.TipoFuncionario;
import com.interceptorsystem.api.domain.vo.CPF;
import com.interceptorsystem.api.domain.vo.Celular;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "funcionario")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private CPF cpf; // <-- Usa o Value Object

    @Column(nullable = false)
    private Celular celular;

    @Column(nullable = false)
    private StatusFuncionario statusFuncionario;

    @Column(nullable = false)
    private TipoEscala tipoEscala;

    @Column(nullable = false)
    private TipoFuncionario tipoFuncionario;

    @Column(nullable = false)
    private BigDecimal salarioMensal;

    @Column(nullable = false)
    private BigDecimal valorTotalBeneficiosMensal;

    @Column(nullable = false)
    private BigDecimal valorDiariaFixa;


}
