package com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.commons.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "api")
public class SecurityPathsProperties {

    private String basePath;
    private Endpoints endpoints;
}
