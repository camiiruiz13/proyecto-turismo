package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tipo_documento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanImagenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanTurismoEntity plan;

    @Column(name = "imagen_base64", columnDefinition = "TEXT")
    private String imagenBase64;

    private Integer orden;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
}
