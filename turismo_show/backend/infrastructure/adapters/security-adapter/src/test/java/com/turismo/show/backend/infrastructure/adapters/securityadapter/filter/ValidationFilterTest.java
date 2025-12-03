package com.turismo.show.backend.infrastructure.adapters.securityadapter.filter;

import com.turismo.show.backend.infrastructure.adapters.securityadapter.auth.AuthenticatedUser;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.TokenJwtConfig;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.properties.Endpoints;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.properties.SecurityPathsProperties;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidationFilterTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private Claims claims ;

    @Mock
    private FilterChain chain;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;
    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private  SecurityPathsProperties properties;

    @InjectMocks
    private ValidationFilter filter;

    private Endpoints endpoints;


    @BeforeEach
    void setup() {
        endpoints = new Endpoints();
        endpoints.setLogin("/login");

        when(properties.getBasePath()).thenReturn("/api");
        when(properties.getEndpoints()).thenReturn(endpoints);
    }



    @Test
    void shouldSkipFilterWhenPathIsLogin() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/api/login");

        filter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
    }

    @Test
    void shouldSkipFilterWhenHeaderIsNull() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/api/secure");
        when(request.getHeader(TokenJwtConfig.HEADER_AUTHORIZATION)).thenReturn(null);

        filter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
    }

    @Test
    void shouldSkipFilterWhenHeaderIsInvalid() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/api/secure");
        when(request.getHeader(TokenJwtConfig.HEADER_AUTHORIZATION)).thenReturn("InvalidToken");

        filter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
    }

    @Test
    void shouldAuthenticateWithValidToken_realJWT() throws Exception {

        when(request.getRequestURI()).thenReturn("/api/secure");

        String authoritiesJson = "[{\"authority\":\"ROLE_USER\"}]";

        String token = Jwts.builder()
                .subject("testuser")
                .claim("authorities", authoritiesJson)
                .signWith(TokenJwtConfig.SECRET_KEY)
                .compact();

        when(request.getHeader(TokenJwtConfig.HEADER_AUTHORIZATION))
                .thenReturn("Bearer " + token);

        when(objectMapper.readValue(eq(authoritiesJson),
                ArgumentMatchers.<TypeReference<List<Map<String, String>>>>any()))
                .thenReturn(List.of(Map.of("authority", "ROLE_USER")));

        filter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);

        var auth = SecurityContextHolder.getContext().getAuthentication();
        assertEquals("testuser",
                ((AuthenticatedUser) auth.getPrincipal()).getUsername());
    }

    @Test
    void shouldAuthenticateWithValidToken() throws Exception {
        String token = Jwts.builder()
                .subject("testuser")
                .claim("authorities", "[{\"authority\":\"ROLE_USER\"}]")
                .signWith(TokenJwtConfig.SECRET_KEY)
                .compact();

        when(request.getRequestURI()).thenReturn("/api/secure");
        when(request.getHeader(TokenJwtConfig.HEADER_AUTHORIZATION)).thenReturn("Bearer " + token);

        when(objectMapper.readValue(eq("[{\"authority\":\"ROLE_USER\"}]"),
                ArgumentMatchers.<TypeReference<List<Map<String, String>>>>any()))
                .thenReturn(List.of(Map.of("authority", "ROLE_USER")));

        filter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);

        var auth = SecurityContextHolder.getContext().getAuthentication();
        assertEquals("testuser", ((AuthenticatedUser) auth.getPrincipal()).getUsername());
    }

    @Test
    void shouldReturnForbiddenWhenTokenIsInvalid() throws Exception {

        when(request.getRequestURI()).thenReturn("/api/protegido");

        when(request.getHeader("Authorization")).thenReturn("Bearer token.invalido.aqui");

        PrintWriter writer = new PrintWriter(new StringWriter());
        when(response.getWriter()).thenReturn(writer);

        filter.doFilterInternal(request, response, chain);

        verify(response).setStatus(HttpStatus.FORBIDDEN.value());

        verify(chain, never()).doFilter(request, response);
    }





}
