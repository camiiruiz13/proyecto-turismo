package com.turismo.show.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Integer idUsuario;
    private String correo;
    private String clave;
    private String rol;
    private String fotoBase64;
    private Boolean activo;
}
