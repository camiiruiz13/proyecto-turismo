package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.repositories;

import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.PreRegistroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PreRegistroRepository extends JpaRepository<PreRegistroEntity, Long> {

    @Query("""
            SELECT COUNT(pr) > 0 
            FROM PreRegistroEntity pr
            WHERE pr.persona.id = :personaId 
              AND pr.plan.id = :planId 
              AND pr.estado = 'PENDIENTE'
            """)

    Boolean existsPendienteByPersonaAndPlan(Long personaId, Long planId);
}