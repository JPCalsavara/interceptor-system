package com.interceptorsystem.api.domain.entity;

import com.interceptorsystem.api.domain.enums.StatusFuncionario;
import com.interceptorsystem.api.domain.enums.TipoEscala;
import com.interceptorsystem.api.domain.enums.TipoFuncionario;
import com.interceptorsystem.api.domain.vo.CPF;
import com.interceptorsystem.api.domain.vo.Celular;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name = "funcionario")
@Entity
@Getter
@Setter
public class Funcionario {
    @Id
    @GeneratedValue
    private UUID id;

    private String nome;
    private CPF cpf; // <-- Usa o Value Object
    private Celular celular;

    private StatusFuncionario statusFuncionario;
    private TipoEscala tipoEscala;
    private TipoFuncionario tipoFuncionario;
    private BigDecimal salarioMensal;
    private BigDecimal valorTotalBeneficiosMensal;
    private BigDecimal valorDiariaFixa;


}
