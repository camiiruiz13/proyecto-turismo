package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.entities;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.entities.enums.EstadoPago;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "pago")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private PersonaEntity persona;

    @ManyToOne
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanTurismoEntity plan;

    @Column(name = "referencia_externa")
    private String referenciaExterna;

    @Enumerated(EnumType.STRING)
    private EstadoPago status;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
}
