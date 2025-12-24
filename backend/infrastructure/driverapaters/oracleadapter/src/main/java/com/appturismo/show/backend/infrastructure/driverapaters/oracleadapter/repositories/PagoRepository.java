package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.repositories;

import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<PagoEntity, Long> {

    Boolean existsByPersona_IdAndPlanIdAndStatus(Long personaId, Long planId, String status);
}