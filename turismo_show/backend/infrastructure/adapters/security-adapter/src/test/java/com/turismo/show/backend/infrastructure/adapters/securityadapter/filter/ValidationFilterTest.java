package com.turismo.show.backend.infrastructure.adapters.securityadapter.filter;

import com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.properties.Endpoints;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.properties.SecurityPathsProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationFilterTest {
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private SecurityPathsProperties properties;
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain chain;

    @InjectMocks
    private ValidationFilter filter;

    private final Endpoints endpoints = new Endpoints();

    @BeforeEach
    void setUp() {
        endpoints.setLogin("/login");
        when(properties.getEndpoints()).thenReturn(endpoints);
    }

    @Test
    void shouldBypassFilterForLoginPath() throws Exception {
        when(properties.getBasePath()).thenReturn("/api");
        when(request.getRequestURI()).thenReturn("/api/login");

        filter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
    }

}