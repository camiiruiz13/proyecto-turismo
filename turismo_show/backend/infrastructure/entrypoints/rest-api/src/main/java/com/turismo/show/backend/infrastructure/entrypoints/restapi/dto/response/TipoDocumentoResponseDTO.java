package com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumentoResponseDTO {

    private Integer idTipoDocumento;
    private String codigo;
    private String descripcion;
}
