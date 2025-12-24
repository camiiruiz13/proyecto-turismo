package com.appturismo.show.backend.infrastructure.driverapaters.redisdapter.exception;

import com.appturismo.show.backend.infrastructure.driverapaters.redisdapter.commons.ErrorCacheCodes;
import lombok.Getter;

@Getter
public class CacheNoEncontradaException  extends RuntimeException {

    private final String code = ErrorCacheCodes.CACHE_NOT_FOUND;
    private final String className;

    public CacheNoEncontradaException(String className) {
        super("No existe información en cache para la clase: " + className);
        this.className = className;
    }
}
