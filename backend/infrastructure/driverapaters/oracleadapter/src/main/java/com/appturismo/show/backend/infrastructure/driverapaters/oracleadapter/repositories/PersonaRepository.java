package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.repositories;

import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {

    Optional<PersonaEntity> findByEmail(String email);

    Optional<PersonaEntity> findByTipoDocumentoIdAndNumeroDocumento(Long tipoDocumentoId, String numeroDocumento);
}