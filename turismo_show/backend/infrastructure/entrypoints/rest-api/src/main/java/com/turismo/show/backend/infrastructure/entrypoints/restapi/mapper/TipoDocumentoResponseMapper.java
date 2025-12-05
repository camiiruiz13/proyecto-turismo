package com.turismo.show.backend.infrastructure.entrypoints.restapi.mapper;

import com.turismo.show.backend.domain.model.TipoDocumento;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.TipoDocumentoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface TipoDocumentoResponseMapper {

    TipoDocumentoResponseDTO toResponse(TipoDocumento tipoDocumento);

    List<TipoDocumentoResponseDTO> toResponseList(List<TipoDocumento> tipoDocumentos);
}
