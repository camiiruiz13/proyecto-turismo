package com.appturismo.show.backend.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Persona {
    private Long idPersona;
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
}
