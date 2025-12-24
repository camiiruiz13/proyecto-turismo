package com.appturismo.show.backend.infrastructure.driverapaters.redisdapter;

import com.appturismo.show.backend.domain.model.gateway.CacheGateway;
import com.appturismo.show.backend.infrastructure.driverapaters.redisdapter.commons.ErrorMessages;
import com.appturismo.show.backend.infrastructure.driverapaters.redisdapter.exception.CacheNoEncontradaException;
import com.appturismo.show.backend.infrastructure.driverapaters.redisdapter.exception.CacheParametricaException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RedisCacheParametricaAdapter<T> implements CacheGateway<T> {

    private static final Duration TTL = Duration.ofHours(6);
    private static final String PREFIX = "catalogo:";


    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByClass(String className) {
        try {
            String key = buildKey(className);
            Object raw = redisTemplate.opsForValue().get(key);
            if (raw == null) {
                throw new CacheNoEncontradaException(className);
            }
            return (List<T>) raw;
        } catch (CacheNoEncontradaException e) {
            throw e;
        }catch (Exception e) {
            throw new CacheParametricaException(
                    String.format(ErrorMessages.CACHE_GET_ERROR, className),
                    e
            );
        }
    }


@Override
public void saveByClass(String className, List<T> value) {
    try {
        String key = buildKey(className);
        redisTemplate.opsForValue().set(key, value, TTL);
    } catch (Exception e) {
        throw new CacheParametricaException(
                String.format(ErrorMessages.CACHE_SAVE_ERROR, className),
                e
        );
    }
}

@Override
public void invalidateByClass(String className) {

    try {
        String key = buildKey(className);
        redisTemplate.delete(key);
    } catch (Exception e) {
        throw new CacheParametricaException(
                String.format(ErrorMessages.CACHE_INVALIDATE_ERROR, className),
                e
        );
    }


}

@Override
public Boolean existsByClass(String className) {
    try {

        String key = buildKey(className);
        Boolean exists = redisTemplate.hasKey(key);
        return Boolean.TRUE.equals(exists);

    }catch (Exception e) {
        throw new CacheParametricaException(
                String.format(ErrorMessages.CACHE_EXISTS_ERROR, className),
                e
        );
    }

}

    @Override
    public Set<String> keys() {
        try {
            Set<String> keys = new HashSet<>();

            ScanOptions options = ScanOptions.scanOptions()
                    .match(PREFIX + "*")
                    .count(1000)
                    .build();

            Cursor<byte[]> cursor = redisTemplate
                    .getConnectionFactory()
                    .getConnection()
                    .scan(options);

            while (cursor.hasNext()) {
                keys.add(new String(cursor.next(), StandardCharsets.UTF_8));
            }

            cursor.close();
            return keys;

        } catch (Exception e) {
            throw new CacheParametricaException(
                    ErrorMessages.CACHE_KEYS_ERROR,
                    e
            );
        }
    }

    private String buildKey(String className) {
    return PREFIX + className;
}
}
