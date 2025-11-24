package com.turismo.show.backend.infrastructure.entrypoints.restapi.util;

import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.ErrorResponseDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.SuccesResponseDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseUtils {

    public static ErrorResponseDTO buildErrorResponse(String error, String message) {
        return ErrorResponseDTO.builder()
                .message(error)
                .error(message)
                .build();
    }
    public static SuccesResponseDTO buildSuccessResponse(String message) {
      return buildSuccessResponse(message, null);
    }
    public static SuccesResponseDTO buildSuccessResponse(String message, Object data) {
        if (data == null) {
            return SuccesResponseDTO.builder()
                    .message(message)
                    .build();
        }
        return SuccesResponseDTO.builder()
                .message(message)
                .data(data)
                .build();
    }
}
