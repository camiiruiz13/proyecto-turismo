package com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorModel {
    private String error;
    private String route;
    private String message;
}
