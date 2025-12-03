package com.turismo.show.backend.infrastructure.adapters.securityadapter.exception;

public class AutenticationException extends RuntimeException{

    public AutenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutenticationException(String message) {
        super(message);
    }
}
