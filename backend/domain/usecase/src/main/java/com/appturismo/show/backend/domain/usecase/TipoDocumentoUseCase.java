package com.appturismo.show.backend.domain.usecase;

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
public class TipoDocumentoUseCase {

    private final TipoDocumentoGateway tipoDocumentoGateway;
    private final CacheGateway<TipoDocumento> cacheGateway;

    public List<TipoDocumento> findAll(String className) {

        if (!TIPODOC.getClassName().equalsIgnoreCase(className))
            throw new InvalidCountryNameException(className);
        List<TipoDocumento> tipos = tipoDocumentoGateway.findAll();

        if (tipos == null || tipos.isEmpty()) {
            throw new TipoDocumentoNotFoundException();
        }

        if (!cacheGateway.existsByClass(className)) {
            cacheGateway.saveByClass(className, tipos);
            return cacheGateway.findByClass(className);
        }

        List<TipoDocumento> cachedList = cacheGateway.findByClass(className);

        if (hasNewItems(tipos, cachedList)) {
            cacheGateway.saveByClass(className, tipos);
            return cacheGateway.findByClass(className);
        }

        return cachedList;

    }

    private Boolean hasNewItems(
            List<TipoDocumento> dbList,
            List<TipoDocumento> cachedList){
        Set<String> cachedCodes = cachedList.stream()
                .map(TipoDocumento::getCodigo)
                .collect(Collectors.toSet());

        return dbList.stream()
                .map(TipoDocumento::getCodigo)
                .filter(Objects::nonNull)
                .anyMatch(code -> !cachedCodes.contains(code));
    }



}
