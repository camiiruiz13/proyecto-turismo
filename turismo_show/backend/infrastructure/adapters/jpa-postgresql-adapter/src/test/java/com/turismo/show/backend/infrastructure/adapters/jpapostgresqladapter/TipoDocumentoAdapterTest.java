package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter;

import com.turismo.show.backend.domain.model.TipoDocumento;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.entities.TipoDocumentoEntity;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.exception.InfrastructureDatabaseException;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.mapper.TipoDocumentoModelMapper;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.repositories.TipoDocumentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TipoDocumentoAdapterTest {
    @Mock
    private  TipoDocumentoModelMapper tipoDocumentoModelMapper;
    @Mock
    private  TipoDocumentoRepository tipoDocumentoRepository;

    @InjectMocks
    private TipoDocumentoAdapter tipoDocumentoAdapter;

    @Test
    void findAll_successfulExecution_returnsMappedList() {

        TipoDocumentoEntity entity = TipoDocumentoEntity.builder()
                .id(1)
                .codigo("CC")
                .descripcion("Cédula de ciudadanía")
                .build();

        TipoDocumento model = TipoDocumento.builder()
                .idTipoDocumento(1)
                .codigo("CC")
                .descripcion("Cédula de ciudadanía")
                .build();

        List<TipoDocumentoEntity> entityList = List.of(entity);
        List<TipoDocumento> modelList = List.of(model);

        when(tipoDocumentoRepository.findAll()).thenReturn(entityList);
        when(tipoDocumentoModelMapper.toModelList(entityList)).thenReturn(modelList);

        // Act
        List<TipoDocumento> result = tipoDocumentoAdapter.findAll();

        // Assert
        assertEquals(modelList, result);
    }

    @Test
    void findAll_repositoryThrowsException_throwsInfrastructureDatabaseException() {
        RuntimeException ex = new RuntimeException("Error DB");

        when(tipoDocumentoRepository.findAll()).thenThrow(ex);

        InfrastructureDatabaseException thrown = assertThrows(
                InfrastructureDatabaseException.class,
                () -> tipoDocumentoAdapter.findAll()
        );


        assertEquals(ex, thrown.getCause());
    }
}