package com.turismo.show.backend.infrastructure.entrypoints.restapi.commons.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "openapi")
public class OpenApiProperties {
    private String title;
    private String version;
    private String description;
    private String terms;

    private License license;

    private SecuritySchemeConfig security;
}
