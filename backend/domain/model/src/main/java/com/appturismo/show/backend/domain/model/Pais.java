package com.appturismo.show.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pais {
    private Long idPais;
    private String iso2;
    private String iso3;
    private String nombre;
    private String prefijo;
}
