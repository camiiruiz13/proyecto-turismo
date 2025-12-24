package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.repositories;

import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsernameAndActivoTrue(String username);
    Optional<UsuarioEntity> findByPersonaIdAndActivoTrue(Long personaId);

    Boolean existsByPersona_IdAndActivoTrue(Long personaId);
}