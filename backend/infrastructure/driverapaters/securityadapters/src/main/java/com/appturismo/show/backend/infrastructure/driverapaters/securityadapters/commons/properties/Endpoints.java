package com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.commons.properties;

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
    private CatalogoPaths catalogos;
}