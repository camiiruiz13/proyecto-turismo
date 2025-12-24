package com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi;

import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.advices.GlobalErrorFilter;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.commons.properties.ApiPathsProperties;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.handler.GetCatalogoByAtributeHandler;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.handler.GetCatalogoHandler;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.handler.LoginHandler;
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
    private final GetCatalogoHandler getCatalogoHandler;
    private final GetCatalogoByAtributeHandler getCatalogoByAtributeHandler;
    private final GlobalErrorFilter errorFilter;
    private final LoginHandler loginHandler;

    @Bean
    public RouterFunction<ServerResponse> apiRoutes() {
        String basePath = apiPathsProperties.getBasePath();

        String catalogoByClass = basePath
                + apiPathsProperties.getEndpoints().getCatalogos().getBase()
                + apiPathsProperties.getEndpoints().getCatalogos().getByClass();

        String catalogoFilter = basePath
                + apiPathsProperties.getEndpoints().getCatalogos().getBase()
                + apiPathsProperties.getEndpoints().getCatalogos().getFilter();

        String loginPath = apiPathsProperties.getEndpoints().getLogin();

        return RouterFunctions.route()
                .GET(catalogoByClass, getCatalogoHandler::findAll)
                .POST(catalogoFilter, getCatalogoByAtributeHandler::findByAtributte)
                .POST(basePath + loginPath, loginHandler::handleLogin)
                .filter(errorFilter)
                .build();
    }
    }

