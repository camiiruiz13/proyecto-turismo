package com.appturismo.show.backend.domain.model.gateway;

import com.appturismo.show.backend.domain.model.Persona;

public interface PersonaGateway {

    Persona save(Persona persona);
    Persona findByEmailOrTipoDocumentoAndNumeroDocumento(
            String email,
            Long idTipoDocumento,
            String numeroDocumento
    );
}
