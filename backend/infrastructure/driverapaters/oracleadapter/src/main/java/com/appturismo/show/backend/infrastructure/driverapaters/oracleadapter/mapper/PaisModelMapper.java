package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.mapper;

import com.appturismo.show.backend.domain.model.Pais;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.PaisEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface PaisModelMapper {

    @Mappings({
            @Mapping(source = "id", target = "idPais"),
            @Mapping(source = "prefijoTelefono", target = "prefijo")
    })
    Pais toModel(PaisEntity entity);

    @Mappings({
            @Mapping(source = "idPais", target = "id"),
            @Mapping(source = "prefijo", target = "prefijoTelefono")
    })
    PaisEntity toEntity(Pais model);

    List<Pais> toModelList(List<PaisEntity> entities);

    List<PaisEntity> toEntityList(List<Pais> models);
}
