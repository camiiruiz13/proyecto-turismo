package com.turismo.show.backend.infrastructure.adapters.securityadapter;

import com.turismo.show.backend.domain.model.User;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.TokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationGatewayAdapterTest {

    @Mock
    private TokenProvider tokenProvider;


    @InjectMocks
    private AuthenticationGatewayAdapter adapter;

    @Test
    void shouldGenerateTokenSuccessfully() {
        User user = User.builder()
                .idUsuario(1)
                .correo("user@test.com")
                .rol("ROLE_USER")
                .build();

        String expectedToken = "jwt.token.generated";
        ArgumentCaptor<Authentication> authCaptor = ArgumentCaptor.forClass(Authentication.class);

        when(tokenProvider.generateTokenResponse(authCaptor.capture())).thenReturn(expectedToken);

        String result = adapter.generateToken(user);

        assertEquals(expectedToken, result);
    }
}