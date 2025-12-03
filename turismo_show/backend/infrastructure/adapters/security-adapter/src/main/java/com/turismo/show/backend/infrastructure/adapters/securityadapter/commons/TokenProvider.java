package com.turismo.show.backend.infrastructure.adapters.securityadapter.commons;

import com.turismo.show.backend.infrastructure.adapters.securityadapter.auth.AuthenticatedUser;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.exception.AutenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final ObjectMapper mapper;

    private static final String AUTHORITIES = "authorities";
    private static final String USERNAME = "username";

    public String generateTokenResponse(Authentication authResult) {
        try {
            AuthenticatedUser user = (AuthenticatedUser) authResult.getPrincipal();
            String username = user.getUsername();
            String idUser = user.getIdUser();

            String authorities = mapper.writeValueAsString(authResult.getAuthorities());

            Claims claims = Jwts.claims()
                    .add(AUTHORITIES, authorities)
                    .add(USERNAME, username)
                    .build();

            return Jwts.builder()
                    .id(idUser)
                    .subject(username)
                    .claims(claims)
                    .expiration(new Date(System.currentTimeMillis() + 3600000))
                    .issuedAt(new Date())
                    .signWith(TokenJwtConfig.SECRET_KEY)
                    .compact();

        } catch (Exception e) {
            throw new AutenticationException("Error generating token", e);
        }
    }
}
