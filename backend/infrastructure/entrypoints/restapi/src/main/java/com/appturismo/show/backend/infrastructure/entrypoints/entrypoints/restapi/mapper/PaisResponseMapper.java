package com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.mapper;

import com.appturismo.show.backend.domain.model.Pais;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.dto.response.PaisDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface PaisResponseMapper {

    PaisDTO toResponse(Pais pais);

    List<PaisDTO> toResponseList(List<Pais> paises);
}
