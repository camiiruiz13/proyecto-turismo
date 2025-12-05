package com.turismo.show.backend.domain.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final String errorMessage;
    public UserNotFoundException(String correo) {
        super("Usuario no encontrado con correo: " + correo);
        this.errorMessage = "USER_NOT_FOUND";
    }
}

