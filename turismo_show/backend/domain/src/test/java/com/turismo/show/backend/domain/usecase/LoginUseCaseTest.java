package com.turismo.show.backend.domain.usecase;

import com.turismo.show.backend.domain.exception.UserNotFoundException;
import com.turismo.show.backend.domain.gateway.AuthenticationGateway;
import com.turismo.show.backend.domain.gateway.PasswordGateway;
import com.turismo.show.backend.domain.gateway.UserGateway;
import com.turismo.show.backend.domain.model.User;
import com.turismo.show.backend.domain.model.login.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseTest {

    @Mock
    private UserGateway userGateway;

    @Mock
    private PasswordGateway passwordGateway;

    @Mock
    private AuthenticationGateway authenticationGateway;

    @InjectMocks
    private LoginUseCase loginUseCase;

    private final String username = "user@test.com";
    private final String password = "1234";

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .idUsuario(1)
                .correo(username)
                .clave("hashed_password")
                .rol("ADMIN")
                .fotoBase64("base64Image")
                .build();
    }

    @Test
    void login_successful() {
        when(userGateway.findByCorreo(username)).thenReturn(user);

        when(authenticationGateway.generateToken(user)).thenReturn("token-jwt");

        Login result = loginUseCase.login(username, password);

        assertNotNull(result);
    }


    @Test
    void login_userNotFound_throwsException() {
        when(userGateway.findByCorreo(username)).thenReturn(null);

        UserNotFoundException ex = assertThrows(
                UserNotFoundException.class,
                () -> loginUseCase.login(username, password)
        );

        assertEquals("Usuario no encontrado con correo: " + username, ex.getMessage());

    }


}