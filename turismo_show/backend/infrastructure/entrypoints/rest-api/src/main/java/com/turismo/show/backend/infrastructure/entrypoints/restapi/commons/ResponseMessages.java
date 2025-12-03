package com.turismo.show.backend.infrastructure.entrypoints.restapi.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessages {
    SESSION_SUCCES("Inicio de sesion exitoso");
    private final String message;
}
