package com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ErrorResponseDTO implements Serializable {
    private String error;
    private String message;
}
