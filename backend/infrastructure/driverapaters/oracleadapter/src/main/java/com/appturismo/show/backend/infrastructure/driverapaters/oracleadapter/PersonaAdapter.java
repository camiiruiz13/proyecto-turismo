package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter;

import com.appturismo.show.backend.domain.model.Persona;
import com.appturismo.show.backend.domain.model.gateway.PersonaGateway;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.PersonaEntity;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.mapper.PersonaModelMapper;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.repositories.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PersonaAdapter implements PersonaGateway {

    private final PersonaRepository personaRepository;
    private final PersonaModelMapper mapper;

    @Override
    @Transactional
    public Persona save(Persona persona) {
        PersonaEntity entity = mapper.toEntity(persona);
        entity = personaRepository.save(entity);
        return mapper.toModel(entity);
    }

    @Override
    public Persona findByEmailOrTipoDocumentoAndNumeroDocumento(String email, Long idTipoDocumento, String numeroDocumento) {
        return null;
    }
}
