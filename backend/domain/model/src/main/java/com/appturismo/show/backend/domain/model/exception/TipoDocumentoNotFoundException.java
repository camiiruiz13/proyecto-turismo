package com.appturismo.show.backend.domain.model.exception;

import lombok.Getter;

@Getter
public class TipoDocumentoNotFoundException extends RuntimeException {

    private final String errorMessage;
    public TipoDocumentoNotFoundException() {
        super("No existen tipos de documento registrados en el sistema.");
        this.errorMessage = "TIPO_DOCUMENTO_NOT_FOUND";
    }
}
