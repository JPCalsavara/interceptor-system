package com.interceptorsystem.api.entity;

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
    
    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private StatusAlocacao statusAlocacao;

    @Column(nullable = false)
    private TipoAlocacao tipoAlocacao;

    // --- Relacionamento: Muitas Alocações para UM Posto de Trabalho ---
    @ManyToOne
    @JoinColumn(name = "posto_de_trabalho_id")
    private PostoDeTrabalhoEntity postoDeTrabalho;

    // --- Relacionamento: Muitas Alocações para UM Funcionário ---
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private FuncionarioE funcionario;
}
