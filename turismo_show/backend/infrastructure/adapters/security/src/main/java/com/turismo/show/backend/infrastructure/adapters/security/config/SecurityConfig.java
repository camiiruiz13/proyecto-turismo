package com.turismo.show.backend.infrastructure.adapters.security.config;

import com.turismo.show.backend.infrastructure.adapters.security.commons.properties.ApiPathsProperties;
import com.turismo.show.backend.infrastructure.adapters.security.filter.AuthenticationFilter;
import com.turismo.show.backend.infrastructure.adapters.security.handler.CustomAccessDeniedHandler;
import com.turismo.show.backend.infrastructure.adapters.security.handler.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final ApiPathsProperties apiPathsProperties;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        String base = apiPathsProperties.getBasePath();
        String login = base + apiPathsProperties.getEndpoints().getLogin();

        String[] WHITE_LIST_URL = {
                login,
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/configuration/**",
                "/webjars/**"
        };

        return http.authorizeHttpRequests((authz) -> authz
                .requestMatchers(WHITE_LIST_URL).permitAll()
                .anyRequest().authenticated()).httpBasic(Customizer.withDefaults())
                .exceptionHandling(ex -> ex.accessDeniedHandler(customAccessDeniedHandler)
                        .authenticationEntryPoint(customAuthenticationEntryPoint))
                .addFilter(new AuthenticationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }


}
