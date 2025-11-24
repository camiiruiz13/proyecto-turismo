package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.repositories;

import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByUsernameAndActivoTrue(String username);
}