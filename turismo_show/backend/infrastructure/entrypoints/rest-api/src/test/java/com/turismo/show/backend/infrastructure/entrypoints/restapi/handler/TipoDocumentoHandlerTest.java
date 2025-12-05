package com.turismo.show.backend.infrastructure.entrypoints.restapi.handler;

import com.turismo.show.backend.domain.model.TipoDocumento;
import com.turismo.show.backend.domain.usecase.TipoDocumentoUseCase;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.TipoDocumentoResponseDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.mapper.TipoDocumentoResponseMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TipoDocumentoHandlerTest {

    @Mock
    private TipoDocumentoUseCase tipoDocumentoUseCase;

    @Mock
    private TipoDocumentoResponseMapper tipoDocumentoResponseMapper;

    @Mock
    private ServerRequest serverRequest;

    @InjectMocks
    private TipoDocumentoHandler handler;



    @Test
    void shouldReturnTipoDocumentoListSuccessfully() {
        // Arrange: entidades del dominio
        TipoDocumento tipo1 = TipoDocumento.builder()
                .idTipoDocumento(1)
                .codigo("CC")
                .descripcion("Cédula de ciudadanía")
                .build();

        TipoDocumento tipo2 = TipoDocumento.builder()
                .idTipoDocumento(2)
                .codigo("TI")
                .descripcion("Tarjeta de identidad")
                .build();

        List<TipoDocumento> domainList = List.of(tipo1, tipo2);

        // DTOs mapeados
        TipoDocumentoResponseDTO dto1 = TipoDocumentoResponseDTO.builder()
                .idTipoDocumento(1)
                .codigo("CC")
                .descripcion("Cédula de ciudadanía")
                .build();

        TipoDocumentoResponseDTO dto2 = TipoDocumentoResponseDTO.builder()
                .idTipoDocumento(2)
                .codigo("TI")
                .descripcion("Tarjeta de identidad")
                .build();

        List<TipoDocumentoResponseDTO> dtoList = List.of(dto1, dto2);

        when(tipoDocumentoUseCase.findAll()).thenReturn(domainList);
        when(tipoDocumentoResponseMapper.toResponseList(domainList)).thenReturn(dtoList);

        // Act
        ServerResponse response = handler.findAll(serverRequest);

        // Assert
        assertEquals(200, response.statusCode().value());
    }
}


