package com.appturismo.show.backend.domain.usecase;

import com.appturismo.show.backend.domain.model.constants.ClassNameConstants;
import com.appturismo.show.backend.domain.model.gateway.CacheGateway;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class ListCacheKeysUseCase {

    private static final String PREFIX = "catalogo:";

    private final CacheGateway cacheAdminGateway;

    public String execute(String classParam) {
        ClassNameConstants classEnum = ClassNameConstants.from(classParam);
        String className = classEnum.getClassName();
        String expectedKey = PREFIX + className;

        Set<String> keys = cacheAdminGateway.keys();

        return keys == null
                ? className
                : keys.stream()
                .filter(expectedKey::equals)
                .findFirst()
                .orElse(className);

    }
}
