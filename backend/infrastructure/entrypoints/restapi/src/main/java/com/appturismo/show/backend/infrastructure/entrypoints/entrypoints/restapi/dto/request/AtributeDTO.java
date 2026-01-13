package com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class AtributeDTO {

    @NotBlank(message = "className es obligatorio")
    @Size(max = 50, message = "className no puede superar 50 caracteres")
    private String className;

    @NotBlank(message = "attributeName es obligatorio")
    @Size(max = 50, message = "attributeName no puede superar 50 caracteres")
    private String attributeName;

    @NotBlank(message = "attributeValue es obligatorio")
    @Size(max = 100, message = "attributeValue no puede superar 100 caracteres")
    private String attributeValue;
}

