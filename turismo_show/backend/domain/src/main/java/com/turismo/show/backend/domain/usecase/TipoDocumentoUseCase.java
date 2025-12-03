package com.turismo.show.backend.domain.usecase;

import com.turismo.show.backend.domain.exception.TipoDocumentoNotFoundException;
import com.turismo.show.backend.domain.gateway.TipoDocumentoGateway;
import com.turismo.show.backend.domain.model.TipoDocumento;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TipoDocumentoUseCase {

    private final TipoDocumentoGateway tipoDocumentoGateway;

    public List<TipoDocumento> findAll() {
        List<TipoDocumento> tipos = tipoDocumentoGateway.findAll();

        if (tipos == null || tipos.isEmpty()) {
            throw new TipoDocumentoNotFoundException();
        }

        return tipos;
    }
}
