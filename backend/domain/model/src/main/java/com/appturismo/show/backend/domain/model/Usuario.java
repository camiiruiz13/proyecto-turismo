package com.appturismo.show.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    private Long idUsuario;
    private Long idPersona;
    private String rol;
    private String username;
    private String password;
    private String foto;

}
