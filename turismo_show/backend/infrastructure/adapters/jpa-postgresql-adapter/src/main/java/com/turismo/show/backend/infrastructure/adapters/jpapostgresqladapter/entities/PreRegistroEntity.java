package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.entities;


import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.entities.enums.EstadoPreRegistro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pre_registro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreRegistroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private PersonaEntity persona;

    @ManyToOne
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanTurismoEntity plan;

    @Enumerated(EnumType.STRING)
    private EstadoPreRegistro estado;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}
