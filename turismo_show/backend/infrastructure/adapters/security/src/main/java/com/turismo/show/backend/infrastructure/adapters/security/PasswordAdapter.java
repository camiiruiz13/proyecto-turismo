package com.turismo.show.backend.infrastructure.adapters.security;

import com.turismo.show.backend.infrastructure.adapters.security.exception.AutenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.turismo.show.backend.domain.gateway.PasswordGateway;
import org.springframework.stereotype.Component;

import static com.turismo.show.backend.infrastructure.adapters.security.commons.ErrorConstants.ERROR_CREDENCIALES;

@Component
@RequiredArgsConstructor
@Slf4j
public class PasswordAdapter implements PasswordGateway {

    private final PasswordEncoder passwordEncoder;
    @Override
    public Boolean esClaveValida(String password, String passwordBD) {
        if (!passwordEncoder.matches(password, passwordBD))
            throw new AutenticationException(ERROR_CREDENCIALES.getMessage());
        return Boolean.TRUE;
    }
}
