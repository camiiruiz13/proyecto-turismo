package com.appturismo.show.backend.domain.model.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final String errorMessage;
    public UserNotFoundException(String correo) {
        super("Usuario no encontrado con correo: " + correo);
        this.errorMessage = "USER_NOT_FOUND";
    }
}

