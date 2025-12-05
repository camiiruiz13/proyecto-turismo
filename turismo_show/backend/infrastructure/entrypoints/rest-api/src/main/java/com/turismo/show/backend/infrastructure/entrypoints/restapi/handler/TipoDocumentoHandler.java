package com.turismo.show.backend.infrastructure.entrypoints.restapi.handler;

import com.turismo.show.backend.domain.usecase.TipoDocumentoUseCase;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.SuccesResponseDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.mapper.TipoDocumentoResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import static com.turismo.show.backend.infrastructure.entrypoints.restapi.commons.ResponseMessages.TIPO_DOCUMENTO_LIST_OK;

@Component
@RequiredArgsConstructor
@Slf4j
public class TipoDocumentoHandler {

    private final TipoDocumentoUseCase useCase;
    private final TipoDocumentoResponseMapper mapper;

    public ServerResponse findAll(ServerRequest request) {

        log.info("Inicio consulta tipos de documento");

        var response = SuccesResponseDTO.builder()
                .data(mapper.toResponseList(useCase.findAll()))
                .message(TIPO_DOCUMENTO_LIST_OK.getMessage())
                .build();

        return ServerResponse.ok().body(response);
    }
}
