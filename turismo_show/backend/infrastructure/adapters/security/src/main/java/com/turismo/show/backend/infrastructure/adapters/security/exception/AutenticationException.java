package com.turismo.show.backend.infrastructure.adapters.security.exception;

public class AutenticationException extends RuntimeException{

    public AutenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutenticationException(String message) {
        super(message);
    }
}
