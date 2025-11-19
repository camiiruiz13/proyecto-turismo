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
    private Long idUser;
    private String username;
    private String token;
    private List<String> roles;
}
