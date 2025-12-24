package com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.appturismo.show.backend.domain.model.TipoDocumento}
 */


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class TipoDocumentoResponseDTO implements Serializable {
    private Long idTipoDocumento;
    private String codigo;
    private String descripcion;
}