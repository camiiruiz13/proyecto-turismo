package com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.handler;

import com.appturismo.show.backend.domain.usecase.ListCacheKeysUseCase;
import com.appturismo.show.backend.domain.usecase.PaisUseCase;
import com.appturismo.show.backend.domain.usecase.TipoDocumentoUseCase;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.dto.response.SuccesResponseDTO;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.mapper.PaisResponseMapper;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.mapper.TipoDocumentoResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;


import static com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.commons.ClassNameConstans.PAISES;
import static com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.commons.ClassNameConstans.TIPODOCUMENTO;
import static com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.commons.ResponseMessages.PAIS_LIST_OK;
import static com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.commons.ResponseMessages.TIPO_DOCUMENTO_LIST_OK;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetCatalogoHandler {

    private final ListCacheKeysUseCase listCacheKeysUseCase;
    private final PaisUseCase paisUseCase;
    private final TipoDocumentoUseCase tipoDocumentoUseCase;
    private final PaisResponseMapper paisResponseMapper;
    private final TipoDocumentoResponseMapper tipoDocumentoResponseMapper;


    public ServerResponse findAll(ServerRequest request) {

        String className = request.pathVariable("className");
        String result = listCacheKeysUseCase.execute(className);
        SuccesResponseDTO response = null;
        log.info("Inicio consulta por tipo de clases");


        if (result.contains(TIPODOCUMENTO))
        {
        response = SuccesResponseDTO.builder()
                .data(tipoDocumentoResponseMapper.toResponseList(tipoDocumentoUseCase.findAll(className)))
                .message(TIPO_DOCUMENTO_LIST_OK.getMessage())
                .build();
        }else if (result.contains(PAISES)){

            response = SuccesResponseDTO.builder()
                    .data(paisResponseMapper.toResponseList(paisUseCase.findAll(className)))
                    .message(PAIS_LIST_OK.getMessage())
                    .build();

        }

        return ServerResponse.ok().body(response);

    }
}
