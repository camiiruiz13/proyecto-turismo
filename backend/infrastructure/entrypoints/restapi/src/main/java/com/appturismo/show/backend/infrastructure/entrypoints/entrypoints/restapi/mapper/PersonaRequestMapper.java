package com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.mapper;

import com.appturismo.show.backend.domain.model.Persona;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.dto.request.PersonaRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface PersonaRequestMapper {

    @Mapping(target = "idPersona", ignore = true)
    Persona toModel(PersonaRequestDTO requestDTO);
}
