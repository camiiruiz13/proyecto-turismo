package com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class PersonaRequestDTO {

    private String nombres;
    private String apellidos;

    private Long idTipoDocumento;

    private String numeroDocumento;
    private String fechaNacimiento;
    private String ciudadNacimiento;

    private String fechaExpedicionDocumento;
    private String ciudadExpedicion;
    private String direccion;
    private String ciudad;
    private Long idPais;
    private String email;
    private String telefonoPersonal;
    private String telefonoWhatsapp;
    private String username;
    private String password;
    private String foto;
}
