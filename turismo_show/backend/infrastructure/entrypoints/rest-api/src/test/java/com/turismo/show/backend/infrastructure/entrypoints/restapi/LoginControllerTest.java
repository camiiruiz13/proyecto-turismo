package com.turismo.show.backend.infrastructure.entrypoints.restapi;

import com.turismo.show.backend.domain.model.login.Login;
import com.turismo.show.backend.domain.usecase.LoginUseCase;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.commons.properties.ApiPathsProperties;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.request.LoginDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.AuthResponseDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.SuccesResponseDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.mapper.LoginResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {
    @Mock
    private LoginUseCase loginUseCase;

    @Mock
    private ApiPathsProperties apiPathsProperties;

    @Mock
    private LoginResponseMapper loginResponseMapper;

    @InjectMocks
    private LoginController controller;

    @Test
    void login_debeRetornar200YToken() {
        // Arrange
        String correo = "user@correo.com";
        String password = "clave123";
        LoginDTO request = new LoginDTO(correo, password);

        Login model = new Login(); // ajusta si es necesario
        AuthResponseDTO authResponseDTO = AuthResponseDTO.builder()
                .idUsuario(1)
                .username("usuario123")
                .rol("ADMIN")
                .foto("foto.png")
                .token("jwt-token-abc")
                .build();


        when(loginUseCase.login(correo, password)).thenReturn(model);
        when(loginResponseMapper.toResponse(model)).thenReturn(authResponseDTO);

        // Act
        ResponseEntity<SuccesResponseDTO> response = controller.login(request);

        assertEquals(200, response.getStatusCode().value());
    }
}