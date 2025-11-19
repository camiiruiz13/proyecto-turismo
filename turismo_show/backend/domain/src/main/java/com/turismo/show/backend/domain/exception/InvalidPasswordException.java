package com.turismo.show.backend.domain.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("La contraseña es incorrecta");
    }
}

