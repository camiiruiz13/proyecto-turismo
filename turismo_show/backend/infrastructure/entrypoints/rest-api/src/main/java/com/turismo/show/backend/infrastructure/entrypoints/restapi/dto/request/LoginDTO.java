package com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {


    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo no es válido")
    private String correo;


    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, max = 13, message = "La contraseña debe tener entre 6 y 13 caracteres")
    private String password;
}
