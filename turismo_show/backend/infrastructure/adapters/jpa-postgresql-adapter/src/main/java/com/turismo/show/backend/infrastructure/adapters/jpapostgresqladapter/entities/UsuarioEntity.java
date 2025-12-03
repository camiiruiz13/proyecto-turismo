package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "usuario",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id_persona"}),
                @UniqueConstraint(columnNames = {"username"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private PersonaEntity persona;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity rol;

    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    private Boolean activo;

    @Column(name = "foto_base64")
    private String fotoBase64;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
}
