package com.appturismo.show.backend.infrastructure.driverapaters.redisdapter.commons;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessages {

    public static final String CACHE_GET_ERROR =
            "Error obteniendo cache para la clase: %s";

    public static final String CACHE_SAVE_ERROR =
            "Error guardando cache para la clase: %s";

    public static final String CACHE_INVALIDATE_ERROR =
            "Error invalidando cache para la clase: %s";

    public static final String CACHE_EXISTS_ERROR =
            "Error validando existencia de cache para la clase: %s";

    public static final String CACHE_KEYS_ERROR = "Error listando las keys de cache";


}
