package com.interceptorsystem.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Table(name = "postos_de_trabalho")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "condominio_id")
    private CondominioEntity condominio;
}
