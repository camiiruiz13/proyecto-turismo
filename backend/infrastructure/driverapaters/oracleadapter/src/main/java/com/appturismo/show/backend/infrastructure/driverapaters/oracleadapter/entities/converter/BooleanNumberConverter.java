package com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities.converter;

import jakarta.persistence.AttributeConverter;

public class BooleanNumberConverter implements AttributeConverter<Boolean, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Boolean aBoolean) {
        return aBoolean == null ? null : (aBoolean ? 1 : 0);
    }

    @Override
    public Boolean convertToEntityAttribute(Integer integer) {
        return integer != null && integer == 1;
    }
}
