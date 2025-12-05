package com.turismo.show.backend.infrastructure.entrypoints.restapi.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessages {
    TIPO_DOCUMENTO_LIST_OK("Lista de tipos de documento obtenida exitosamente"),
    SESSION_SUCCES("Inicio de sesion exitoso");
    private final String message;
}
