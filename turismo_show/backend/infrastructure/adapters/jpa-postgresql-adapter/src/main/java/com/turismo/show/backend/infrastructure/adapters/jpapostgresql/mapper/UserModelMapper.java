package com.turismo.show.backend.infrastructure.adapters.jpapostgresql.mapper;

import com.turismo.show.backend.domain.model.User;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresql.entities.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface UserModelMapper {

    @Mapping(source = "id", target = "idUsuario")
    @Mapping(source = "username", target = "correo")
    @Mapping(source = "passwordHash", target = "clave")
    @Mapping(source = "rol.nombre", target = "rol")
    @Mapping(source = "fotoBase64", target = "fotoBase64")
    @Mapping(source = "activo", target = "activo")
    User toModel(UsuarioEntity entity);
}
