package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PLAN_CLIENTE")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class PlanClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_USUARIO")
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PLAN")
    private PlanTurismoEntity plan;

    private LocalDateTime fechaAsignacion;
}