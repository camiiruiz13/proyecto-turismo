package com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.mapper;

import com.appturismo.show.backend.domain.model.User;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.dto.request.PersonaRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface UserRequestMapper {
    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "rol", ignore = true)
    User toModel(PersonaRequestDTO requestDTO);
}
