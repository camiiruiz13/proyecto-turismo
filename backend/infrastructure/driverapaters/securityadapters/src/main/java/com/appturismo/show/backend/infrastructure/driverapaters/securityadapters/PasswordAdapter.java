package com.appturismo.show.backend.infrastructure.driverapaters.securityadapters;

import com.appturismo.show.backend.domain.model.gateway.PasswordGateway;
import com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.exception.AutenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.commons.ErrorConstants.ERROR_CREDENCIALES;

@Component
@RequiredArgsConstructor
@Slf4j
public class PasswordAdapter implements PasswordGateway {

    private final PasswordEncoder passwordEncoder;
    @Override
    public Boolean esClaveValida(String password, String passwordBD) {
        log.info("========== TEST HASH ==========");
        log.info("Password recibido: [" + password + "]");
        log.info("Password BD: [" + passwordBD + "]");
        log.info("Hash nuevo BCrypt: [" + passwordEncoder.encode(password) + "]");
        log.info("Matcher resultado: " + passwordEncoder.matches(password, passwordBD));
        log.info("================================");
        if (!passwordEncoder.matches(password, passwordBD))
            throw new AutenticationException(ERROR_CREDENCIALES.getMessage());
        return Boolean.TRUE;
    }
}