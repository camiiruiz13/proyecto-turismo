package com.turismo.show.backend.domain.exception;

import lombok.Getter;

@Getter
public class InvalidCountryNameException  extends RuntimeException{

    private final String errorMessage;
    public InvalidCountryNameException(String message) {
        super("Clase no encontrada: " + message);
        this.errorMessage = "CLASS_NOT_FOUND";
    }
}
