package com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.mapper;


import com.appturismo.show.backend.domain.model.login.Login;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.dto.response.AuthResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface LoginResponseMapper {

    AuthResponseDTO toResponse(Login login);
}
