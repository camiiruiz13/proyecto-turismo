package com.turismo.show.backend.infrastructure.adapters.security.commons.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "api")
public class ApiPathsProperties {

    private String basePath;
    private Endpoints endpoints;
}
