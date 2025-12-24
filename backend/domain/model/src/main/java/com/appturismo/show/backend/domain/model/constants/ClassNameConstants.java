package com.appturismo.show.backend.domain.model.constants;

import com.appturismo.show.backend.domain.model.exception.InvalidCountryNameException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ClassNameConstants {

    PAISES("Paises"),
    TIPODOC("TipoDocumento");

    private final String className;

    public static ClassNameConstants from(String value) {

        return Arrays.stream(values())
                .filter(v ->  v.getClassName().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new InvalidCountryNameException(value));
    }
}
