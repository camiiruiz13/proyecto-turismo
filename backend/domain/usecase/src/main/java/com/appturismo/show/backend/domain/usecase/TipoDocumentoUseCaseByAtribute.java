package com.appturismo.show.backend.domain.usecase;


import com.appturismo.show.backend.domain.model.AtributeModel;
import com.appturismo.show.backend.domain.model.TipoDocumento;
import com.appturismo.show.backend.domain.model.exception.InvalidCountryNameException;
import com.appturismo.show.backend.domain.model.exception.TipoDocumentoNotFoundException;
import com.appturismo.show.backend.domain.model.gateway.CacheGateway;
import com.appturismo.show.backend.domain.model.gateway.TipoDocumentoGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.appturismo.show.backend.domain.model.constants.ClassNameConstants.TIPODOC;


@RequiredArgsConstructor
public class TipoDocumentoUseCaseByAtribute {

    private final TipoDocumentoGateway tipoDocumentoGateway;
    private final CacheGateway<TipoDocumento> cacheGateway;

    public TipoDocumento findByAtributte(AtributeModel atributeModel) {

        if (!TIPODOC.getClassName().equalsIgnoreCase(atributeModel.getClassName())) {
            throw new InvalidCountryNameException(atributeModel.getClassName());
        }

        List<TipoDocumento> tipoDocumento = tipoDocumentoGateway.findAll();

        if (tipoDocumento == null || tipoDocumento.isEmpty()) {
            throw new TipoDocumentoNotFoundException();
        }

        String attributeName = atributeModel.getAttributeName();
        String attributeValue = atributeModel.getAttributeValue();
        String className = atributeModel.getClassName();


        List<TipoDocumento> cachedList = cacheGateway.existsByClass(className)
                ? cacheGateway.findByClass(className)
                : null;

        boolean mustRefreshCache =
                cachedList == null ||
                        cachedList.isEmpty() ||
                        hasNewItems(tipoDocumento, cachedList);


        if (mustRefreshCache) {
            cacheGateway.saveByClass(className, tipoDocumento);
        }
        List<TipoDocumento> baseList = cacheGateway.findByClass(className);


        return baseList.stream()
                .filter(p -> matchesByAttribute(p, attributeName, attributeValue))
                .findFirst()
                .orElseThrow(TipoDocumentoNotFoundException::new);
    }


    private Boolean hasNewItems(
            List<TipoDocumento> dbList,
            List<TipoDocumento> cachedList) {
        Set<String> cachedCodes = cachedList.stream()
                .map(TipoDocumento::getCodigo)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        return dbList.stream()
                .map(TipoDocumento::getCodigo)
                .filter(Objects::nonNull)
                .anyMatch(code -> !cachedCodes.contains(code));

    }

    private boolean matchesByAttribute(TipoDocumento TipoDocumento, String attributeName, String expectedValue) {


        String attr = attributeName.trim().toLowerCase();

        return switch (attr) {
            case "idTipoDocumento" -> TipoDocumento.getIdTipoDocumento() != null &&
                    TipoDocumento.getIdTipoDocumento().toString().equals(expectedValue.trim());

            case "codigo" -> TipoDocumento.getCodigo() != null &&
                    TipoDocumento.getCodigo().equalsIgnoreCase(expectedValue.trim());

            case "descripcion" -> TipoDocumento.getDescripcion() != null &&
                    TipoDocumento.getDescripcion().equalsIgnoreCase(expectedValue.trim());
            default -> false;
        };
    }
}
