package com.interceptorsystem.api.domain.entity;

import com.interceptorsystem.api.domain.enums.StatusCondominio;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Table(name="condominio")
@Entity
@Getter
@Setter
public class Condominio {
    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    private String cnpj;

    private StatusCondominio status;


}
