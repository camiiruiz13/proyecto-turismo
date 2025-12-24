package com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessages {
    INVALID_ATTRIBUTE("Atributo inválido"),
    CACHE_KEY_NOT_FOUND("Clave de caché no encontrada"),
    REDIS_INTERNAL_ERROR("Error interno de Redis"),
    SESSION_SUCCES("Inicio de sesion exitoso"),
    PAIS_LIST_OK("Lista de paises obtenida exitosamente"),
    PAIS_OK("Pais obtenido exitosamente"),
    TIPO_DOCUMENTO_OK("Tipo de documento obtenido exitosamente"),
    TIPO_DOCUMENTO_LIST_OK("Lista de tipos de documento obtenida exitosamente");
    private final String message;
}
