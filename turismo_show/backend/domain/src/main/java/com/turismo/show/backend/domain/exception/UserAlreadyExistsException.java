package com.turismo.show.backend.domain.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String correo) {
        super("Ya existe un usuario registrado con el correo: " + correo);
    }
}
