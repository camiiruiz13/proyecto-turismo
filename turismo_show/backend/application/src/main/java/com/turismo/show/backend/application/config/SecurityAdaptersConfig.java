package com.turismo.show.backend.application.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = "com.turismo.show.backend.infrastructure.adapters.security"
)
public class SecurityAdaptersConfig {
}
