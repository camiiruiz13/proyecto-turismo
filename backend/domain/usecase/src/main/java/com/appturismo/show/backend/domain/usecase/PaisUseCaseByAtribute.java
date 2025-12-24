package com.appturismo.show.backend.domain.usecase;


import com.appturismo.show.backend.domain.model.AtributeModel;
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
public class PaisUseCaseByAtribute {

    private final PaisGateway paisGateway;
    private final CacheGateway<Pais> cacheGateway;

    public Pais findByAtributte(AtributeModel atributeModel) {

        if (!PAISES.getClassName().equalsIgnoreCase(atributeModel.getClassName())) {
            throw new InvalidCountryNameException(atributeModel.getClassName());
        }

        List<Pais> paises = paisGateway.findAll();
        if (paises == null || paises.isEmpty()) {
            throw new PaisNotFoundException();
        }

        String attributeName = atributeModel.getAttributeName();
        String attributeValue = atributeModel.getAttributeValue();
        String className = atributeModel.getClassName();

        // --- cache logic (igual al que dejaste en TipoDocumento) ---
        List<Pais> cachedList = cacheGateway.existsByClass(className)
                ? cacheGateway.findByClass(className)
                : null;

        boolean mustRefreshCache =
                cachedList == null ||
                        cachedList.isEmpty() ||
                        hasNewItems(paises, cachedList);

        if (mustRefreshCache) {
            cacheGateway.saveByClass(className, paises);
        }

        List<Pais> baseList = cacheGateway.findByClass(className);


        return baseList.stream()
                .filter(p -> matchesByAttribute(p, attributeName, attributeValue))
                .findFirst()
                .orElseThrow(PaisNotFoundException::new);
    }

    private Boolean hasNewItems(List<Pais> dbList, List<Pais> cachedList) {
        Set<String> cachedCodes = cachedList.stream()
                .map(Pais::getIso2)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        return dbList.stream()
                .map(Pais::getIso2)
                .filter(Objects::nonNull)
                .anyMatch(code -> !cachedCodes.contains(code));
    }

    private boolean matchesByAttribute(Pais pais, String attributeName, String expectedValue) {

        String attr = attributeName.trim().toLowerCase();

        return switch (attr) {
            case "idpais" ->
                    pais.getIdPais() != null &&
                            pais.getIdPais().toString().equals(expectedValue.trim());

            case "iso2" ->
                    pais.getIso2() != null &&
                            pais.getIso2().equalsIgnoreCase(expectedValue.trim());

            case "iso3" ->
                    pais.getIso3() != null &&
                            pais.getIso3().equalsIgnoreCase(expectedValue.trim());

            case "nombre" ->
                    pais.getNombre() != null &&
                            pais.getNombre().equalsIgnoreCase(expectedValue.trim());

            case "prefijo" ->
                    pais.getPrefijo() != null &&
                            pais.getPrefijo().equalsIgnoreCase(expectedValue.trim());

            default -> false;
        };
    }
}
