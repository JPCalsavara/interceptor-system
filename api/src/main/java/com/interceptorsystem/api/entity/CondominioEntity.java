package com.interceptorsystem.api.entity;

import com.interceptorsystem.api.domain.enums.StatusCondominio;
import com.interceptorsystem.api.domain.vo.CNPJ;
import com.interceptorsystem.api.domain.vo.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "condominios") // É uma boa prática usar nomes de tabela no plural
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CondominioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private StatusCondominio status;

    @Embedded
    @AttributeOverride(name = "valor", column = @Column(name = "cnpj", nullable = false, unique = true))
    private CNPJ cnpj; // <-- Correção aqui

    @Embedded
    private Endereco endereco;
}