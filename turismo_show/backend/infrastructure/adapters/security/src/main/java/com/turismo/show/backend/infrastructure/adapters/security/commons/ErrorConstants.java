package com.turismo.show.backend.infrastructure.adapters.security.commons;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorConstants {
    INVALID_TOKEN("No autorizado: token inválido o no enviado"),
    ERROR_CREDENCIALES("Error al leer las credenciales del usuario");

    private final String message;
}
