package com.turismo.show.backend.infrastructure.adapters.jpapostgresql.exception;

import lombok.Getter;

@Getter
public class InfrastructureDatabaseException extends RuntimeException {

    private final String errorMessage;

    public InfrastructureDatabaseException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public InfrastructureDatabaseException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
    }


}
