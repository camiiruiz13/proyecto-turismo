package com.turismo.show.backend.infrastructure.adapters.jpapostgresql.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "persona",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id_tipo_documento", "numero_documento"}),
                @UniqueConstraint(columnNames = {"email"})
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
    private Integer id;

    // Datos básicos
    private String nombres;
    private String apellidos;

    // Identificación
    @ManyToOne
    @JoinColumn(name = "id_tipo_documento", nullable = false)
    private TipoDocumentoEntity tipoDocumento;

    @Column(name = "numero_documento", nullable = false, length = 30)
    private String numeroDocumento;

    // Datos requeridos
    private LocalDate fechaNacimiento;

    @Column(name = "ciudad_nacimiento", length = 100)
    private String ciudadNacimiento;

    @Column(name = "fecha_expedicion_documento")
    private LocalDate fechaExpedicionDocumento;

    @Column(name = "ciudad_expedicion", length = 100)
    private String ciudadExpedicion;

    // Domicilio
    private String direccion;

    @Column(length = 100)
    private String ciudad;

    @ManyToOne
    @JoinColumn(name = "id_pais", nullable = false)
    private PaisEntity pais;

    // Contacto
    private String email;

    @Column(name = "telefono_personal")
    private String telefonoPersonal;

    @Column(name = "telefono_whatsapp")
    private String telefonoWhatsapp;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
}
