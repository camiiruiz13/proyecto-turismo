package com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDTO {
    private Integer idUsuario;
    private String username;
    private String rol;
    private String foto;
    private String token;
}
