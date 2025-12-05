package com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endpoints {
    private String login;
    private String tipoDocumento;
}
