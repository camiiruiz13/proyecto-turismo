package com.turismo.show.backend.infrastructure.adapters.securityadapter.config;

import com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.TokenProvider;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.properties.SecurityPathsProperties;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.filter.ValidationFilter;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.handler.CustomAccessDeniedHandler;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.handler.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tools.jackson.databind.ObjectMapper;

@Configuration
@RequiredArgsConstructor
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
        String tipoDocumento = base + properties.getEndpoints().getTipoDocumento();

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers(login, tipoDocumento).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterAfter(validationFilter, UsernamePasswordAuthenticationFilter.class) // solo este
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
