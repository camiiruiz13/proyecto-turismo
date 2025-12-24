package com.turismo.show.backend.domain.gateway;

import com.turismo.show.backend.domain.model.Pais;

import java.util.List;

public interface CacheGateway<T> {
    List<T> consultarPorNombreClase(String nombreClase);

    List<T> buscarPorAtributo(String nombreClase, String atributo, Object valor);

    void refrescarCache(String nombreClase);
}
