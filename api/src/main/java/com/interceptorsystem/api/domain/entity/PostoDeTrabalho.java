package com.interceptorsystem.api.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Table(name = "posto-de-trabalho")
@Entity
@Getter
@Setter
public class PostoDeTrabalho {
    @Id
    @GeneratedValue
    private UUID id;

    private String descricao;
    private LocalTime horarioInicio;
    private LocalTime horarioFim;
}
