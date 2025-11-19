package com.turismo.show.backend.infrastructure.entrypoints.restapi.util;

import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.ErrorResponseDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseUtils {

    public static ErrorResponseDTO buildErrorResponse(String error, String message) {
        return ErrorResponseDTO.builder()
                .message(error)
                .error(message)
                .build();
    }
}
