package com.turismo.show.backend.infrastructure.entrypoints.restapi;

import com.turismo.show.backend.infrastructure.entrypoints.restapi.advices.GlobalErrorFilter;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.commons.properties.ApiPathsProperties;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.handler.LoginHandler;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.handler.TipoDocumentoHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class ApiRouterConfig {

    private final ApiPathsProperties apiPathsProperties;
    private final LoginHandler loginHandler;
    private final TipoDocumentoHandler tipoDocumentoHandler;
    private final GlobalErrorFilter errorFilter;

    @Bean
    public RouterFunction<ServerResponse> apiRoutes() {
        String basePath = apiPathsProperties.getBasePath();
        String loginPath = apiPathsProperties.getEndpoints().getLogin();
        String tipoDocumentoPath = apiPathsProperties.getEndpoints().getTipoDocumento();
        return RouterFunctions.route()
                .POST(basePath + loginPath, loginHandler::handleLogin)
                .GET(basePath + tipoDocumentoPath, tipoDocumentoHandler::findAll)
                .filter(errorFilter)
                .build();
    }
}
