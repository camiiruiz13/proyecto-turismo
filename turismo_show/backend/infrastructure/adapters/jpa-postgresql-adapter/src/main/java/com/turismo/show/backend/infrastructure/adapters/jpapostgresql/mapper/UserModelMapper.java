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

    @Mapping(target = "idUsuario", source = "id")
    @Mapping(target = "correo", source = "username")
    @Mapping(target = "clave", source = "passwordHash")
    @Mapping(target = "rol", source = "rol.nombre")
    @Mapping(target = "activo", source = "activo")
    @Mapping(target = "fotoBase64", source = "fotoBase64")
    User toModel(UsuarioEntity entity);

    @Mapping(target = "id", source = "idUsuario")
    @Mapping(target = "username", source = "correo")
    @Mapping(target = "passwordHash", source = "clave")
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "persona", ignore = true)
    @Mapping(target = "fotoBase64", source = "fotoBase64")
    @Mapping(target = "fechaCreacion", ignore = true)
    UsuarioEntity toEntity(User model);

}
