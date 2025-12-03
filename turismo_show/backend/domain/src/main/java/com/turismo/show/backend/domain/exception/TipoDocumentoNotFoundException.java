package com.turismo.show.backend.domain.exception;
public class TipoDocumentoNotFoundException extends RuntimeException {

    public TipoDocumentoNotFoundException() {
        super("No existen tipos de documento registrados en el sistema.");
    }
}
