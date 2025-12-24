package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.repositories;

import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.PlanTurismoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanTurismoRepository extends JpaRepository<PlanTurismoEntity, Long> {

    Boolean existsByIdAndActivoTrue(Long id);
}