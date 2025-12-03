package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter;

import com.turismo.show.backend.domain.gateway.TipoDocumentoGateway;
import com.turismo.show.backend.domain.model.TipoDocumento;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.mapper.TipoDocumentoModelMapper;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.repositories.TipoDocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TipoDocumentoAdapter implements TipoDocumentoGateway {

    private final TipoDocumentoModelMapper tipoDocumentoModelMapper;
    private final TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    public List<TipoDocumento> findAll() {
        return null;
    }
}
