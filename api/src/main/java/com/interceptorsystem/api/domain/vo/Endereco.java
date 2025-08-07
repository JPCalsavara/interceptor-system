package com.interceptorsystem.api.domain.vo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Table(name = "endereco")
@Entity
@Getter
@Setter
public class Endereco {
    @Id
    @GeneratedValue
    private UUID id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    @ManyToOne
    @JoinColumn(name = "condominio_id")
    private com.interceptorsystem.api.entity.CondominioEntity condominio;
}
