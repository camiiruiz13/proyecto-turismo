package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter;

import com.appturismo.show.backend.domain.model.Pais;
import com.appturismo.show.backend.domain.model.gateway.PaisGateway;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.commons.DatabaseErrorMessages;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.exception.InfrastructureDatabaseException;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.mapper.PaisModelMapper;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.repositories.PaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaisAdapter implements PaisGateway {

    private final PaisModelMapper paisModelMapper;
    private final PaisRepository paisRepository;

    @Override
    public List<Pais> findAll() {
        try {
            return paisModelMapper.toModelList(paisRepository.findAll());
        } catch (Exception ex) {
            throw new InfrastructureDatabaseException(DatabaseErrorMessages.QUERY_EXECUTION_FAILED, ex);
        }
    }
}

