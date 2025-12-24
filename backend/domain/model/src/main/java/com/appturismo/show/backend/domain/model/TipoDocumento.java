package com.appturismo.show.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumento {

    private Long idTipoDocumento;
    private String codigo;
    private String descripcion;
}
