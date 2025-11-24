package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 2)
    private String iso2;

    @Column(nullable = false, unique = true, length = 3)
    private String iso3;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 10)
    private String prefijoTelefono;
}