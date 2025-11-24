package com.turismo.show.backend.infrastructure.entrypoints.restapi.commons;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SwaggerConstants {

    public static final String TAG_NAME_LOGIN = "Login";

    public static final String CORREO_DESC = "Correo electrónico del usuario";
    public static final String TAG_DESCRIPTION_LOGIN = "Operacion para inicio de sesón";
    public static final String CORREO_EXAMPLE = "usuario@example.com";

    public static final String CLAVE_DESC = "Clave del usuario, entre 6 y 13 caracteres";
    public static final String CLAVE_EXAMPLE = "clave123";
    public static final int CLAVE_MIN = 6;
    public static final int CLAVE_MAX = 13;

    public static final String HTTP_200 = "200";
    public static final String HTTP_201 = "201";
    public static final String HTTP_400 = "400";
    public static final String HTTP_401 = "401";
    public static final String HTTP_409 = "409";
    public static final String HTTP_500 = "500";

    public static final String LOGIN_SUMMARY = "Inicia sesion";
    public static final String LOGIN_DESCRIPTION = "Permite al usuario iniciar sesion";

    public static final String LOGIN_SUCCESS = "Inicio de sesión exitoso";
    public static final String LOGIN_UNAUTHORIZED = "Credenciales inválidas o usuario no autorizado";
    public static final String LOGIN_INVALID_DATA = "Datos de inicio de sesión inválidos o incompletos";
    public static final String LOGIN_INTERNAL_ERROR = "Error interno durante el proceso de autenticación";
    public static final String LOGIN_USER_NOT_FOUND = "El usuario no existe en el sistema";
    public static final String LOGIN_CONFLICT = "El usuario ya existe en el sistema";
    public static final String LOGIN_CREATED = "Usuario creado correctamente";

    public static final String DESCRIPTION_REQUEST = "Estructura esperada para el campo `request`";
    public static final String CONTENT_TYPE = "application/json";

}
