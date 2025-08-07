package com.interceptorsystem.api.domain.entity;

import com.interceptorsystem.api.domain.enums.StatusAlocacao;
import com.interceptorsystem.api.domain.enums.TipoAlocacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "alocacao")
@Entity
@Getter
@Setter
public class AlocacaoEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private LocalDate data;


    private StatusAlocacao statusAlocacao;


    private TipoAlocacao tipoAlocacao;

    // --- Relacionamento: Muitas Alocações para UM Posto de Trabalho ---
    @ManyToOne
    @JoinColumn(name = "posto_de_trabalho_id")
    private PostoDeTrabalho postoDeTrabalho;

    // --- Relacionamento: Muitas Alocações para UM Funcionário ---
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
}
