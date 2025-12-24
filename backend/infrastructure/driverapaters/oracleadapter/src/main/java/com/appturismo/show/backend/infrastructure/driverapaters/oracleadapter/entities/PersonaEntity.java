package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "PERSONA",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"EMAIL"}),
                @UniqueConstraint(columnNames = {"ID_TIPO_DOCUMENTO", "NUMERO_DOCUMENTO"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_TIPO_DOCUMENTO")
    private TipoDocumentoEntity tipoDocumento;

    @Column(nullable = false, length = 30)
    private String numeroDocumento;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false, length = 100)
    private String ciudadNacimiento;

    @Column(nullable = false)
    private LocalDate fechaExpedicionDocumento;

    @Column(nullable = false, length = 100)
    private String ciudadExpedicion;

    @Column(nullable = false, length = 250)
    private String direccion;

    @Column(nullable = false, length = 100)
    private String ciudad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PAIS")
    private PaisEntity pais;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(length = 20)
    private String telefonoPersonal;

    @Column(length = 20)
    private String telefonoWhatsapp;

    private LocalDateTime fechaCreacion;
}