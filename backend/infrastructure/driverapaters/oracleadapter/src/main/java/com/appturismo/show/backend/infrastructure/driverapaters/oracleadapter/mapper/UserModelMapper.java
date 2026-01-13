package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.mapper;


import com.appturismo.show.backend.domain.model.User;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.UsuarioEntity;
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
