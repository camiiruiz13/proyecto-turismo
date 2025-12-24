package com.appturismo.show.backend.infrastructure.driverapaters.redisdapter.exception;

import com.appturismo.show.backend.infrastructure.driverapaters.redisdapter.commons.ErrorCacheCodes;
import lombok.Getter;

@Getter
public class CacheParametricaException extends RuntimeException {

    private final String code = ErrorCacheCodes.CACHE_ERROR;

    public CacheParametricaException(String message, Throwable cause) {
        super(message, cause);
    }
}
