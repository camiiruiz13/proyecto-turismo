package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter;

import com.turismo.show.backend.domain.gateway.TipoDocumentoGateway;
import com.turismo.show.backend.domain.model.TipoDocumento;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.commons.DatabaseErrorMessages;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.exception.InfrastructureDatabaseException;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.mapper.TipoDocumentoModelMapper;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.repositories.TipoDocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TipoDocumentoAdapter implements TipoDocumentoGateway {

    private final TipoDocumentoModelMapper tipoDocumentoModelMapper;
    private final TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoDocumento> findAll() {
        try {
            return tipoDocumentoModelMapper.toModelList(tipoDocumentoRepository.findAll());
        }catch (Exception ex) {
            throw new InfrastructureDatabaseException(DatabaseErrorMessages.QUERY_EXECUTION_FAILED, ex);
        }
    }
}
