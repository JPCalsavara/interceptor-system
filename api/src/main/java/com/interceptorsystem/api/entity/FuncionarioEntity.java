package com.interceptorsystem.api.entity;

import com.interceptorsystem.api.domain.enums.StatusFuncionario;
import com.interceptorsystem.api.domain.enums.TipoEscala;
import com.interceptorsystem.api.domain.enums.TipoFuncionario;
import com.interceptorsystem.api.domain.vo.CPF;
import com.interceptorsystem.api.domain.vo.Celular;
import com.interceptorsystem.api.domain.vo.Endereco; // Assuming you added this
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "funcionarios")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Embedded
    @AttributeOverride(name = "valor", column = @Column(name = "cpf", nullable = false, unique = true))
    private CPF cpf;

    @Embedded
    @AttributeOverride(name = "valor", column = @Column(name = "celular", nullable = false))
    private Celular celular;

    @Embedded
    private Endereco endereco; // Endereco doesn't have a 'valor' field, so no override is needed.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusFuncionario statusFuncionario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEscala tipoEscala;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoFuncionario tipoFuncionario;
}