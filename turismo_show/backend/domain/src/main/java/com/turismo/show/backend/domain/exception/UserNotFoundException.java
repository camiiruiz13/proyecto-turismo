package com.turismo.show.backend.domain.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String correo) {
        super("Usuario no encontrado con correo: " + correo);
    }
}

