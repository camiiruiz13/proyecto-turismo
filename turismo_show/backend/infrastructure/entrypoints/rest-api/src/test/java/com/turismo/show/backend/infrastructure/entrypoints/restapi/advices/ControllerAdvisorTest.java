package com.turismo.show.backend.infrastructure.entrypoints.restapi.advices;

import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.commons.DatabaseErrorMessages;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.exception.InfrastructureDatabaseException;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.ErrorResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerAdvisorTest {

    private ControllerAdvisor advisor;

    @BeforeEach
    void setup() {
        advisor = new ControllerAdvisor();
    }

    @Test
    void handleDatabaseException_debeRetornar500YErrorResponse() {
        // Arrange
        InfrastructureDatabaseException ex = new InfrastructureDatabaseException(DatabaseErrorMessages.QUERY_EXECUTION_FAILED, new RuntimeException());

        // Act
        ResponseEntity<ErrorResponseDTO> response = advisor.handleDatabaseException(ex);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

       
    }
}
