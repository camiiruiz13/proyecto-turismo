package com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaisDTO {
    private Long idPais;
    private String iso2;
    private String iso3;
    private String nombre;
    private String prefijo;
}
