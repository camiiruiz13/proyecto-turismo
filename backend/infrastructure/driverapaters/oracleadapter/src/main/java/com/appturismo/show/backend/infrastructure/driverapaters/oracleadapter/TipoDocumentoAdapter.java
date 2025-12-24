package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter;

import com.appturismo.show.backend.domain.model.TipoDocumento;
import com.appturismo.show.backend.domain.model.gateway.TipoDocumentoGateway;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.commons.DatabaseErrorMessages;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.exception.InfrastructureDatabaseException;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.mapper.TipoDocumentoModelMapper;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.repositories.TipoDocumentoRepository;
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
        try {
            return tipoDocumentoModelMapper.toModelList(tipoDocumentoRepository.findAll());
        }catch (Exception ex) {
            throw new InfrastructureDatabaseException(DatabaseErrorMessages.QUERY_EXECUTION_FAILED, ex);
        }
    }
}
