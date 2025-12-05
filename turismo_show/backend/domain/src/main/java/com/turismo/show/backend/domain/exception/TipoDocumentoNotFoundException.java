package com.turismo.show.backend.domain.exception;

import lombok.Getter;

@Getter
public class TipoDocumentoNotFoundException extends RuntimeException {

 private final String errorMessage;
    public TipoDocumentoNotFoundException() {
        super("No existen tipos de documento registrados en el sistema.");
        this.errorMessage = "TIPO_DOCUMENTO_NOT_FOUND";
    }
}
