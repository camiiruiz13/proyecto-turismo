package com.turismo.show.backend.infrastructure.entrypoints.restapi.config;

import com.turismo.show.backend.infrastructure.entrypoints.restapi.commons.properties.OpenApiProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfiguration {

    private final OpenApiProperties props;

    @Bean
    public OpenAPI customOpenAPI() {

        final String securitySchemeName = props.getSecurity().getName();

        return new OpenAPI()
                .info(new Info()
                        .title(props.getTitle())
                        .version(props.getVersion())
                        .description(props.getDescription())
                        .termsOfService(props.getTerms())
                        .license(new License()
                                .name(props.getLicense().getName())
                                .url(props.getLicense().getUrl()))
                )
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme(props.getSecurity().getScheme())       // bearer
                                        .bearerFormat(props.getSecurity().getFormat()) // JWT
                        )
                );
    }
}
