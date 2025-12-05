package com.turismo.show.backend.domain.usecase;

import com.turismo.show.backend.domain.exception.TipoDocumentoNotFoundException;
import com.turismo.show.backend.domain.gateway.TipoDocumentoGateway;
import com.turismo.show.backend.domain.model.TipoDocumento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TipoDocumentoUseCaseTest {

    @Mock
    private TipoDocumentoGateway tipoDocumentoGateway;

    @InjectMocks
    private TipoDocumentoUseCase tipoDocumentoUseCase;

    @Test
    void findAll_whenDataExists_returnsList() {
        TipoDocumento tipo = TipoDocumento.builder()
                .idTipoDocumento(1)
                .codigo("CC")
                .descripcion("Cédula")
                .build();

        List<TipoDocumento> lista = List.of(tipo);

        when(tipoDocumentoGateway.findAll()).thenReturn(lista);

        List<TipoDocumento> resultado = tipoDocumentoUseCase.findAll();

        assertEquals(lista, resultado);
    }

    @Test
    void findAll_whenGatewayReturnsNull_throwsException() {
        when(tipoDocumentoGateway.findAll()).thenReturn(null);

        assertThrows(TipoDocumentoNotFoundException.class, () -> tipoDocumentoUseCase.findAll());
    }

    @Test
    void findAll_whenGatewayReturnsEmptyList_throwsException() {
        when(tipoDocumentoGateway.findAll()).thenReturn(Collections.emptyList());

        assertThrows(TipoDocumentoNotFoundException.class, () -> tipoDocumentoUseCase.findAll());
    }
}