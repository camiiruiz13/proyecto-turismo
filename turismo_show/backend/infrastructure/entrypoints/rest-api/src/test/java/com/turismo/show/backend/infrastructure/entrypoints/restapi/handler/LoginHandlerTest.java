package com.turismo.show.backend.infrastructure.entrypoints.restapi.handler;

import com.turismo.show.backend.domain.model.login.Login;
import com.turismo.show.backend.domain.usecase.LoginUseCase;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.request.LoginDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.AuthResponseDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.SuccesResponseDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.handler.LoginHandler;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.mapper.LoginResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginHandlerTest {

    @Mock
    private LoginUseCase loginUseCase;

    @Mock
    private LoginResponseMapper loginResponseMapper;

    @Mock
    private ServerRequest serverRequest;

    private LoginHandler loginHandler;

    @BeforeEach
    void setUp() {
        loginHandler = new LoginHandler(loginUseCase, loginResponseMapper);
    }

    @Test
    void shouldHandleLoginSuccessfully() throws Exception {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setCorreo("test@email.com");
        loginDTO.setPassword("123456");

        Login login = Login.builder()
                .idUsuario(1)
                .username("test@email.com")
                .rol("USER")
                .foto("fotoBase64")
                .token("jwt-token")
                .build();

        AuthResponseDTO authResponseDTO = AuthResponseDTO.builder()
                .idUsuario(1)
                .username("test@email.com")
                .rol("USER")
                .foto("fotoBase64")
                .token("jwt-token")
                .build();

        when(serverRequest.body(LoginDTO.class)).thenReturn(loginDTO);
        when(loginUseCase.login("test@email.com", "123456")).thenReturn(login);
        when(loginResponseMapper.toResponse(login)).thenReturn(authResponseDTO);

        // Act
        ServerResponse response = loginHandler.handleLogin(serverRequest);

        // Assert
        assertEquals(200, response.statusCode().value());
    }
}
