package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities;

import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.converter.BooleanNumberConverter;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "PLAN_TURISMO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanTurismoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titulo;

    @Lob
    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    @Convert(converter = BooleanNumberConverter.class)
    private Boolean activo;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
}