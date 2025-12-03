package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.mapper;

import com.turismo.show.backend.domain.model.TipoDocumento;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.entities.TipoDocumentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface TipoDocumentoModelMapper {

    @Mapping(source = "id", target = "idTipoDocumento")
    TipoDocumento toModel(TipoDocumentoEntity entity);

    @Mapping(source = "idTipoDocumento", target = "id")
    TipoDocumentoEntity toEntity(TipoDocumento model);

    List<TipoDocumento> toModelList(List<TipoDocumentoEntity> entities);

    List<TipoDocumentoEntity> toEntityList(List<TipoDocumento> models);
}
