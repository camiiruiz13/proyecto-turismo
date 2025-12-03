package com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.turismo.show.backend.infrastructure.entrypoints.restapi.commons.SwaggerConstants.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @Schema(description = CORREO_DESC, example = CORREO_EXAMPLE, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo no es válido")
    private String correo;

    @Schema(description = CLAVE_DESC, example = CLAVE_EXAMPLE, minLength = CLAVE_MIN, maxLength = CLAVE_MAX, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, max = 13, message = "La contraseña debe tener entre 6 y 13 caracteres")
    private String password;
}
