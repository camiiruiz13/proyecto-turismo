package com.turismo.show.backend.domain.gateway;

import com.turismo.show.backend.domain.model.TipoDocumento;

import java.util.List;

public interface TipoDocumentoGateway {
    List<TipoDocumento> findAll();
}
