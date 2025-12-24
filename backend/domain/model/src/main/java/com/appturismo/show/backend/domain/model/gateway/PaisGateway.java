package com.appturismo.show.backend.domain.model.gateway;

import com.appturismo.show.backend.domain.model.Pais;

import java.util.List;

public interface PaisGateway {

    List<Pais> findAll();
}
