package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.repositories;

import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.PlanClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanClienteRepository extends JpaRepository<PlanClienteEntity, Long> {

    Boolean existsByUsuario_IdAndPlan_Id(Long usuarioId, Long planId);
}