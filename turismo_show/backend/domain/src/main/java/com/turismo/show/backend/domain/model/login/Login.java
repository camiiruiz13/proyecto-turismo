package com.turismo.show.backend.domain.model.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Login {

    private Integer idUsuario;
    private String username;
    private String rol;
    private String foto;
    private String token;
}
