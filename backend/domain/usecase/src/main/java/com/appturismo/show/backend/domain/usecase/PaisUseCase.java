package com.appturismo.show.backend.domain.usecase;


import com.appturismo.show.backend.domain.model.Pais;
import com.appturismo.show.backend.domain.model.exception.InvalidCountryNameException;
import com.appturismo.show.backend.domain.model.exception.PaisNotFoundException;
import com.appturismo.show.backend.domain.model.gateway.CacheGateway;
import com.appturismo.show.backend.domain.model.gateway.PaisGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.appturismo.show.backend.domain.model.constants.ClassNameConstants.PAISES;

@RequiredArgsConstructor
public class PaisUseCase {

    private final PaisGateway paisGateway;
    private final CacheGateway<Pais> cacheGateway;

    public List<Pais> findAll(String className) {
        if (!PAISES.getClassName().equalsIgnoreCase(className))
            throw new InvalidCountryNameException(className);
        List<Pais> paises = paisGateway.findAll();

        if (paises == null || paises.isEmpty()) {
            throw new PaisNotFoundException();
        }if (!cacheGateway.existsByClass(className)) {
            cacheGateway.saveByClass(className, paises);
            return cacheGateway.findByClass(className);
        }

        List<Pais> cachedList = cacheGateway.findByClass(className);

        if (hasNewItems(paises, cachedList)) {
            cacheGateway.saveByClass(className, paises);
            return cacheGateway.findByClass(className);
        }

        return cachedList;
    }

    private Boolean hasNewItems(
            List<Pais> dbList,
            List<Pais> cachedList) {
        Set<String> cachedCodes = cachedList.stream()
                .map(Pais::getIso2)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        return dbList.stream()
                .map(Pais::getIso2)
                .filter(Objects::nonNull)
                .anyMatch(code -> !cachedCodes.contains(code));

    }
}
