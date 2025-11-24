package com.turismo.show.backend.infrastructure.adapters.securityadapter;

import com.turismo.show.backend.domain.gateway.AuthenticationGateway;
import com.turismo.show.backend.domain.model.User;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.auth.AuthenticatedUser;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationGatewayAdapter implements AuthenticationGateway {

    private final TokenProvider tokenProvider;

    @Override
    public String generateToken(User user) {

        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(user.getRol()));

        AuthenticatedUser principal = new AuthenticatedUser(
                user.getIdUsuario().toString(),  // idUser
                user.getCorreo(),                // username
                null,                            // password (no se mete al token)
                authorities
        );

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        principal,
                        null,
                        authorities
                );

        return tokenProvider.generateTokenResponse(authentication);
    }
}