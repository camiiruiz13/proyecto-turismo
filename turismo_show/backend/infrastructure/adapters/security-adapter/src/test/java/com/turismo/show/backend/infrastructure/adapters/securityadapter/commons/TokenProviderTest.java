package com.turismo.show.backend.infrastructure.adapters.securityadapter.commons;


import com.turismo.show.backend.infrastructure.adapters.securityadapter.auth.AuthenticatedUser;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.exception.AutenticationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import tools.jackson.databind.ObjectMapper;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenProviderTest {

    @Mock
    private Authentication authentication;

    @Mock
    private AuthenticatedUser user;

    @Mock
    private GrantedAuthority authority;

    @Mock
    private ObjectMapper mapper;

    @InjectMocks
    private TokenProvider tokenProvider;

    @Test
    void generateTokenResponse_successful_withConcreteAuth() throws Exception {

        when(user.getUsername()).thenReturn("testuser");
        when(user.getIdUser()).thenReturn("123");

        // Autoridades
        GrantedAuthority authority = () -> "ROLE_USER";
        Collection<GrantedAuthority> authorities = List.of(authority);

        // Authentication concreto
        Authentication auth = new Authentication() {
            @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
            @Override public Object getCredentials() { return null; }
            @Override public Object getDetails() { return null; }
            @Override public Object getPrincipal() { return user; }
            @Override public boolean isAuthenticated() { return true; }
            @Override public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {}
            @Override public String getName() { return "testuser"; }
        };

        when(mapper.writeValueAsString(any())).thenReturn("[\"ROLE_USER\"]");

        String token = tokenProvider.generateTokenResponse(auth);

        assertNotNull(token);
    }

    @Test
    void generateTokenResponse_error() throws Exception {
        // Mock para causar error
        when(authentication.getPrincipal()).thenReturn(user);
        when(mapper.writeValueAsString(any())).thenThrow(new RuntimeException("boom"));

        AutenticationException ex = assertThrows(
                AutenticationException.class,
                () -> tokenProvider.generateTokenResponse(authentication)
        );

        assertEquals("Error generating token", ex.getMessage());
    }

}


