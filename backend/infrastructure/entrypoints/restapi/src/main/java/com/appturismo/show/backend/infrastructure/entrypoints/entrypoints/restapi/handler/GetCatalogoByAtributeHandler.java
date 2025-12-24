package com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.handler;

import com.appturismo.show.backend.domain.model.AtributeModel;
import com.appturismo.show.backend.domain.usecase.ListCacheKeysUseCase;
import com.appturismo.show.backend.domain.usecase.PaisUseCaseByAtribute;
import com.appturismo.show.backend.domain.usecase.TipoDocumentoUseCaseByAtribute;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.dto.request.AtributeDTO;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.dto.response.SuccesResponseDTO;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.mapper.PaisResponseMapper;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.mapper.TipoDocumentoResponseMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;

import static com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.commons.ClassNameConstans.PAISES;
import static com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.commons.ClassNameConstans.TIPODOCUMENTO;
import static com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.commons.ResponseMessages.PAIS_OK;
import static com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.commons.ResponseMessages.TIPO_DOCUMENTO_OK;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetCatalogoByAtributeHandler {

    private final ListCacheKeysUseCase listCacheKeysUseCase;
    private final PaisUseCaseByAtribute paisUseCaseByAtribute;
    private final TipoDocumentoUseCaseByAtribute tipoDocumentoUseCaseByAtribute;
    private final PaisResponseMapper paisResponseMapper;
    private final TipoDocumentoResponseMapper tipoDocumentoResponseMapper;
    private final ObjectMapper objectMapper;

    public ServerResponse findByAtributte(ServerRequest request) throws ServletException, IOException {
        AtributeDTO dto = request.body(AtributeDTO.class);
        String result = listCacheKeysUseCase.execute(dto.getClassName());
        SuccesResponseDTO response = null;
        log.info("Inicio consulta por tipo de clases");

        if (result.contains(TIPODOCUMENTO)) {
            response = SuccesResponseDTO.builder()
                    .data(tipoDocumentoUseCaseByAtribute.findByAtributte(objectMapper.convertValue(dto, AtributeModel.class)))
                    .message(TIPO_DOCUMENTO_OK.getMessage())
                    .build();
        }else if (result.contains(PAISES)){
            response = SuccesResponseDTO.builder()
                    .data(paisUseCaseByAtribute.findByAtributte(objectMapper.convertValue(dto, AtributeModel.class)))
                    .message(PAIS_OK.getMessage())
                    .build();
        }

        return ServerResponse.ok().body(response);
    }

}
