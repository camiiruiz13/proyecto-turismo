package com.turismo.show.backend.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.turismo.show.backend.infrastructure.adapters.jpapostgresql.repositories"
)
@EntityScan(
        basePackages = "com.turismo.show.backend.infrastructure.adapters.jpapostgresql.entities"
)
public class JpaConfig {
}
