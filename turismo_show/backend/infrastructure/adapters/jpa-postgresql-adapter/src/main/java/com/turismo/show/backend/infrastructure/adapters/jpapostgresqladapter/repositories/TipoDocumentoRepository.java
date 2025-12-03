package com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.repositories;

import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.entities.TipoDocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumentoEntity, Integer> {
}