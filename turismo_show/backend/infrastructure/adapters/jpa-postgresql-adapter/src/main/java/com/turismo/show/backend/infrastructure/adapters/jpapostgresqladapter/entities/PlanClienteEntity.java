package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "plan_cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "id_plan", nullable = false)
    private PlanTurismoEntity plan;

    @Column(name = "fecha_asignacion")
    private LocalDateTime fechaAsignacion;
}
