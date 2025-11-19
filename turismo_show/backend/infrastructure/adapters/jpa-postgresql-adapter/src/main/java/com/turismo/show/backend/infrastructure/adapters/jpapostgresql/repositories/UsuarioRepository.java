package com.turismo.show.backend.infrastructure.adapters.jpapostgresql.repositories;

import com.turismo.show.backend.infrastructure.adapters.jpapostgresql.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByUsernameAndActivoTrue(String username);
}