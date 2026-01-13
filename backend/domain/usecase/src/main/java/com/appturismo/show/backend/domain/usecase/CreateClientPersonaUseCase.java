package com.appturismo.show.backend.domain.usecase;

import com.appturismo.show.backend.domain.model.Persona;
import com.appturismo.show.backend.domain.model.exception.PersonaAlreadyExistsException;
import com.appturismo.show.backend.domain.model.gateway.PersonaGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateClientPersonaUseCase {

    private final PersonaGateway personaGateway;


    public Persona savePersona(Persona persona){
        Persona existente = personaGateway.findByEmailOrTipoDocumentoAndNumeroDocumento(
                persona.getEmail(),
                persona.getIdTipoDocumento(),
                persona.getNumeroDocumento()
        );

        if (existente!=null && existente.getIdPersona() != null){
            throw  PersonaAlreadyExistsException.byEmailOrDocument(
                    persona.getEmail(),
                    persona.getIdTipoDocumento(),
                    persona.getNumeroDocumento()
            );
        }

        Persona saved = personaGateway.save(persona);
        return saved;


    }
}
