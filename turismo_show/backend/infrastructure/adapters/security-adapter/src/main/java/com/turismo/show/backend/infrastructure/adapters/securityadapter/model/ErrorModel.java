package com.turismo.show.backend.infrastructure.adapters.securityadapter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorModel {
    private String error;
    private String ruta;
    private String mensaje;
}
