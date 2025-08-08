package com.interceptorsystem.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Table(name = "posto-de-trabalho")
@Entity
@Getter
@Setter
public class PostoDeTrabalhoEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalTime horarioInicio;

    @Column(nullable = false)
    private LocalTime horarioFim;
}
