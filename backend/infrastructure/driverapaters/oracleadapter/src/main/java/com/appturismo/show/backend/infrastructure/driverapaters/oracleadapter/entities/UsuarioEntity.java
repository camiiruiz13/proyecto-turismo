package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities;

import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.converter.BooleanNumberConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "USUARIO",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"USERNAME"}),
                @UniqueConstraint(columnNames = {"ID_PERSONA"})
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
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PERSONA")
    private PersonaEntity persona;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ROL")
    private RolEntity rol;

    @Column(nullable = false, length = 150)
    private String username;

    @Lob
    @Column(nullable = false)
    private String passwordHash;

    @Convert(converter = BooleanNumberConverter.class)
    @Column(name = "ACTIVO")
    private Boolean activo;


    @Lob
    private String fotoBase64;

    private LocalDateTime fechaCreacion;
}