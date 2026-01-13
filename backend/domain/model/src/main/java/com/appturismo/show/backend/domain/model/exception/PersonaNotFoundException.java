package com.appturismo.show.backend.domain.model.exception;

public class PersonaNotFoundException extends RuntimeException {

    public PersonaNotFoundException(String message) {
        super(message);
    }

    public static PersonaNotFoundException byId(Long idPersona) {
        return new PersonaNotFoundException(
                "Persona no encontrada con id: " + idPersona
        );
    }
}
