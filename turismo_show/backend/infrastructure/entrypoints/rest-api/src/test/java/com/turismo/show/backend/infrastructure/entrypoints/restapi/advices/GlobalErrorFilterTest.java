package com.turismo.show.backend.infrastructure.entrypoints.restapi.advices;

import com.turismo.show.backend.domain.exception.TipoDocumentoNotFoundException;
import com.turismo.show.backend.domain.exception.UserNotFoundException;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.exception.InfrastructureDatabaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalErrorFilterTest {

    @Mock
    private HandlerFunction<ServerResponse> handlerFunction;

    @Mock
    private ServerRequest serverRequest;

    private GlobalErrorFilter globalErrorFilter;

    @BeforeEach
    void setUp() {
        globalErrorFilter = new GlobalErrorFilter();
    }

    @Test
    void shouldReturnResponseWhenNoExceptionThrown() throws Exception {
        ServerResponse expectedResponse = ServerResponse.ok().build();
        when(handlerFunction.handle(serverRequest)).thenReturn(expectedResponse);

        ServerResponse actualResponse = globalErrorFilter.filter(serverRequest, handlerFunction);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldHandleUserNotFoundException() throws Exception {
        UserNotFoundException exception = new UserNotFoundException("test@email.com");
        when(handlerFunction.handle(serverRequest)).thenThrow(exception);

        ServerResponse response = globalErrorFilter.filter(serverRequest, handlerFunction);

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode());
    }


    @Test
    void shouldHandleTipoDocumentoNotFoundException() throws Exception {
        TipoDocumentoNotFoundException exception = new TipoDocumentoNotFoundException();
        when(handlerFunction.handle(serverRequest)).thenThrow(exception);

        ServerResponse response = globalErrorFilter.filter(serverRequest, handlerFunction);

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode());
    }


    @Test
    void shouldHandleInfrastructureDatabaseException() throws Exception {
        InfrastructureDatabaseException exception =
                new InfrastructureDatabaseException("DB_ERROR", new RuntimeException("DB Error"));

        when(handlerFunction.handle(serverRequest)).thenThrow(exception);

        ServerResponse response = globalErrorFilter.filter(serverRequest, handlerFunction);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.statusCode());
    }



}