package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities;

import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.enums.EstadoPreRegistro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PRE_REGISTRO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreRegistroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PERSONA")
    private PersonaEntity persona;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PLAN")
    private PlanTurismoEntity plan;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPreRegistro estado;

    private LocalDateTime fechaRegistro;
}
