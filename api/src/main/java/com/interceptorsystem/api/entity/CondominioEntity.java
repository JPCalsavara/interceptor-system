package com.interceptorsystem.api.entity;

import com.interceptorsystem.api.domain.enums.StatusCondominio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name="condominio")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CondominioEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "status", nullable = false)
    private StatusCondominio status;

    public CondominioEntity(String nome, String cnpj, StatusCondominio status){
        this.nome = nome;
        this.cnpj = cnpj;
        this.status = status;
    }

}
