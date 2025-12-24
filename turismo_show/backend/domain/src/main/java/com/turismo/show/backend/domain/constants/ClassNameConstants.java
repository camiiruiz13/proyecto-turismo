package com.turismo.show.backend.domain.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClassNameConstants {
    PAISES("Pais"),
    TIPO_DOCUMENTO("TipoDocumento");
    private final String className;
}
