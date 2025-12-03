package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.entities;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "plan_turismo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanTurismoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    private String descripcion;

    private BigDecimal precio;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private Boolean activo;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
}
