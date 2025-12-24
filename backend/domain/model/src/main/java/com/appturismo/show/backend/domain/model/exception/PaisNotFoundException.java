package com.appturismo.show.backend.domain.model.exception;

import lombok.Getter;

@Getter
public class PaisNotFoundException extends RuntimeException {

 private final String errorMessage;
    public PaisNotFoundException() {
        super("No existen paises registrados en el sistema.");
        this.errorMessage = "PAIS_NOT_FOUND";
    }
}
