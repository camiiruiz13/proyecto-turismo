package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities;

import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.enums.EstadoPago;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PAGO")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PERSONA")
    private PersonaEntity persona;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PLAN")
    private PlanTurismoEntity plan;

    private String referenciaExterna;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPago status;

    private LocalDateTime fechaPago;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
}