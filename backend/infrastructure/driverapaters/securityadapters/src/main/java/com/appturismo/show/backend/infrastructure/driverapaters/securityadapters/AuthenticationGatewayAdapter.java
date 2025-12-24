package com.appturismo.show.backend.infrastructure.driverapaters.securityadapters;

import com.appturismo.show.backend.domain.model.User;
import com.appturismo.show.backend.domain.model.gateway.AuthenticationGateway;
import com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.auth.AuthenticatedUser;
import com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.commons.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationGatewayAdapter implements AuthenticationGateway {

    private final TokenProvider tokenProvider;

    @Override
    public String generateToken(User user) {
        log.info("Generando token para usuario con ID: {} y correo: {}", user.getIdUsuario(), user.getCorreo());
        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(user.getRol()));

        log.debug("Autoridades asignadas: {}", authorities);

        AuthenticatedUser principal = new AuthenticatedUser(user.getIdUsuario().toString(),
                user.getCorreo(), null, authorities);

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, authorities);

        log.info("Token generado exitosamente para usuario: {}", user.getCorreo());
        return tokenProvider.generateTokenResponse(authentication);
    }
}