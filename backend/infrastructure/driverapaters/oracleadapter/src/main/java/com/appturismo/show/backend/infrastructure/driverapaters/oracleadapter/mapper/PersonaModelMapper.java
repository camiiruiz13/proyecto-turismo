package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.mapper;

import com.appturismo.show.backend.domain.model.Persona;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.PersonaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface PersonaModelMapper {

    @Mapping(source = "id", target = "idPersona")
    Persona toModel(PersonaEntity entity);

    @Mapping(source = "idPersona", target = "id")
    PersonaEntity toEntity(Persona model);
}


