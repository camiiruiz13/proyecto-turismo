package com.turismo.show.backend.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.turismo.show.backend.infrastructure.adapters.jpapostgresql",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Adapter$")
        },
        useDefaultFilters = true
)
public class JpaAdaptersConfig {}
