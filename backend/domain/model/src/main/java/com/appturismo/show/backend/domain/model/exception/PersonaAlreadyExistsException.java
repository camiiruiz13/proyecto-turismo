package com.appturismo.show.backend.domain.model.exception;

import lombok.Getter;

@Getter
public class PersonaAlreadyExistsException extends RuntimeException {

    private final String errorMessage;

    public PersonaAlreadyExistsException(String message) {
        super(message);
        this.errorMessage = "PERSONA_ALREADY_EXISTS";
    }

    public static PersonaAlreadyExistsException byEmailOrDocument(
            String email,
            Long idTipoDocumento,
            String numeroDocumento
    ) {
        return new PersonaAlreadyExistsException(
                String.format(
                        "La persona ya existe (email=%s, tipoDocumento=%d, numeroDocumento=%s)",
                        email,
                        idTipoDocumento,
                        numeroDocumento
                )
        );
    }
}
