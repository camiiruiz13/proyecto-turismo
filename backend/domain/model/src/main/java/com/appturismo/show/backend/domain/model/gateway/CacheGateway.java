package com.appturismo.show.backend.domain.model.gateway;

import java.util.List;
import java.util.Set;

public interface CacheGateway <T>{
    List<T> findByClass(String className);
    void saveByClass(String className, List<T> value);
    void invalidateByClass(String className);

    Boolean existsByClass(String className);

    Set<String> keys();

}
