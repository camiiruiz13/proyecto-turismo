package com.turismo.show.backend.infrastructure.entrypoints.restapi.commons.properties;

import lombok.Data;

@Data
public class SecuritySchemeConfig {
    private String name;
    private String type;
    private String scheme;
    private String format;
}
