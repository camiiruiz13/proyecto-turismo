package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PLAN_IMAGEN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanImagenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PLAN")
    private PlanTurismoEntity plan;

    @Lob
    @Column(nullable = false)
    private String imagenBase64;

    private Integer orden;
    private LocalDateTime fechaCreacion;
}