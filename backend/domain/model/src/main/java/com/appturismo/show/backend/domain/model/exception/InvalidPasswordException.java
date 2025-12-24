package com.appturismo.show.backend.domain.model.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("La contraseña es incorrecta");
    }
}

