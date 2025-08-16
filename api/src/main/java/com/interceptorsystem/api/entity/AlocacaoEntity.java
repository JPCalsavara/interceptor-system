package com.interceptorsystem.api.entity;

import com.interceptorsystem.api.domain.enums.StatusAlocacao;
import com.interceptorsystem.api.domain.enums.TipoAlocacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "alocacoes")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private FuncionarioEntity funcionario;
}
