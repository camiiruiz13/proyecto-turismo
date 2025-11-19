package com.turismo.show.backend.infrastructure.adapters.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turismo.show.backend.domain.model.User;
import com.turismo.show.backend.infrastructure.adapters.security.auth.AuthenticatedUser;
import com.turismo.show.backend.infrastructure.adapters.security.commons.TokenJwtConfig;
import com.turismo.show.backend.infrastructure.adapters.security.exception.AutenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.turismo.show.backend.infrastructure.adapters.security.commons.ErrorConstants.INVALID_TOKEN;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private static final String AUTHORITIES = "authorities";
    private static final String USERNAME = "username";
    private static final String TOKEN = "token";

    public Authentication authenticateUser(User user) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getCorreo(), user.getClave());
        return authenticationManager.authenticate(authenticationToken);
    }

    public Map<String, Object> generateTokenResponse(Authentication authResult) {
        try {
            AuthenticatedUser user = (AuthenticatedUser) authResult.getPrincipal();
            String username = user.getUsername();
            Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();

            Claims claims = Jwts.claims()
                    .add(AUTHORITIES, new ObjectMapper().writeValueAsString(roles))
                    .add(USERNAME, username)
                    .build();

            String token = Jwts.builder()
                    .subject(username)
                    .claims(claims)
                    .expiration(new Date(System.currentTimeMillis() + 3600000))
                    .issuedAt(new Date())
                    .signWith(TokenJwtConfig.SECRET_KEY)
                    .compact();

            Map<String, Object> body = new HashMap<>();
            body.put(TOKEN, token);
            body.put(USERNAME, username);
            return body;

        } catch (
                Exception e) {
            throw new AutenticationException(INVALID_TOKEN.getMessage(), e);
        }
    }


}
