package com.appturismo.show.backend.domain.model.gateway;

import com.appturismo.show.backend.domain.model.TipoDocumento;

import java.util.List;

public interface TipoDocumentoGateway {

   List<TipoDocumento> findAll();
}
