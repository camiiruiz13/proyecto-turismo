package com.turismo.show.backend.domain.usecase;

import com.turismo.show.backend.domain.gateway.TipoDocumentoGateway;
import com.turismo.show.backend.domain.model.TipoDocumento;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TipoDocumentoUseCase {

    private final TipoDocumentoGateway tipoDocumentoGateway;

    public List<TipoDocumento> findAll() {
        return tipoDocumentoGateway.findAll();
    }
}
