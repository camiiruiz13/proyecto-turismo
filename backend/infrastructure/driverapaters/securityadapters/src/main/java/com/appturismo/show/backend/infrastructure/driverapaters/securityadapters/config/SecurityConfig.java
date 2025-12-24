package com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.config;


import com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.commons.TokenProvider;
import com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.commons.properties.SecurityPathsProperties;
import com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.filter.ValidationFilter;
import com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.handler.CustomAccessDeniedHandler;
import com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.handler.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;



@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final TokenProvider provider;

    private static final String[] WHITE_LIST_URL = {

            // Swagger UI:
            "/swagger-ui/**",
            "/swagger-ui.html",

            // Springdoc internal
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-resources/**",
            "/webjars/**",
            "/configuration/**",

            // Static OpenAPI contract
            "/openapi/**"
    };


    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



    @Bean
    ValidationFilter validationFilter(AuthenticationManager manager,
                                      ObjectMapper mapper,
                                      SecurityPathsProperties properties) {
        return new ValidationFilter(manager, mapper, properties);
    }



    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                    ValidationFilter validationFilter,
                                    SecurityPathsProperties properties) throws Exception {

        String base = properties.getBasePath();
        String login = base + properties.getEndpoints().getLogin();
        String catalogoBase = base + properties.getEndpoints().getCatalogos().getBase();

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .authorizeHttpRequests(auth -> {
                    log.info("➡ Evaluando matchers:");
                    log.info("   - login: {}", login);
                    log.info("   - catalogoBase: {}", catalogoBase);
                    log.info("   - catalogoWildcard: {}", catalogoBase + "/**");

                    auth
                            // Swagger y documentación pública
                            .requestMatchers(WHITE_LIST_URL).permitAll()
                            // Login y catálogo públicos
                            .requestMatchers(login).permitAll()
                            .requestMatchers(catalogoBase).permitAll()
                            .requestMatchers(catalogoBase + "/**").permitAll()
                            // Todo lo demás requiere autenticación
                            .anyRequest().authenticated();
                })
                // tu filtro de validación se ejecuta después de UsernamePasswordAuthenticationFilter
                .addFilterAfter(validationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
