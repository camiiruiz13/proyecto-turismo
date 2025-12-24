package com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.exception;

public class AutenticationException extends RuntimeException{

    public AutenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutenticationException(String message) {
        super(message);
    }
}
