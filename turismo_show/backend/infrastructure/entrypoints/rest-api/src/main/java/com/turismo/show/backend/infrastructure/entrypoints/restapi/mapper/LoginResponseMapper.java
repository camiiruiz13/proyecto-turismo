package com.turismo.show.backend.infrastructure.entrypoints.restapi.mapper;

import com.turismo.show.backend.domain.model.login.Login;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.AuthResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface LoginResponseMapper {

    AuthResponseDTO toResponse(Login login);
}
