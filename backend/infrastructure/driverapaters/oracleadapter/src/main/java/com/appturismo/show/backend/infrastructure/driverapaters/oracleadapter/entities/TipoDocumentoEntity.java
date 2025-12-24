package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TIPO_DOCUMENTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoDocumentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @Column(nullable = false, length = 200)
    private String descripcion;
}
