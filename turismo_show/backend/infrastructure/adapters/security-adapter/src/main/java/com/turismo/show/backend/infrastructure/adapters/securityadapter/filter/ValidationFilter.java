package com.turismo.show.backend.infrastructure.adapters.securityadapter.filter;

import com.turismo.show.backend.domain.gateway.AuthenticationGateway;
import com.turismo.show.backend.domain.model.User;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.auth.AuthenticatedUser;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.TokenJwtConfig;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.properties.SecurityPathsProperties;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.model.ErrorModel;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.util.ResponseAuthorizationUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.ErrorConstants.INVALID_TOKEN;
public class ValidationFilter extends BasicAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final SecurityPathsProperties properties;

    public ValidationFilter(AuthenticationManager authenticationManager,
                            ObjectMapper objectMapper,
                            SecurityPathsProperties properties) {
        super(authenticationManager);
        this.objectMapper = objectMapper;
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws IOException, ServletException {

        String path = request.getRequestURI();
        String base = properties.getBasePath();
        String login = base + properties.getEndpoints().getLogin();


        if (path.contains(login)) {
            chain.doFilter(request, response);
            return;
            
        }
        
        String header = request.getHeader(TokenJwtConfig.HEADER_AUTHORIZATION);

        if (header == null || !header.startsWith(TokenJwtConfig.PREFIX_TOKEN)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            String token = header.replace(TokenJwtConfig.PREFIX_TOKEN, "");

            Claims claims = Jwts.parser()
                    .verifyWith(TokenJwtConfig.SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String username = claims.getSubject();
            String authoritiesJson = (String) claims.get("authorities");

            List<Map<String, String>> rolesList =
                    objectMapper.readValue(authoritiesJson,
                            new TypeReference<>() {});

            List<SimpleGrantedAuthority> authorities = rolesList.stream()
                    .map(r -> new SimpleGrantedAuthority(r.get("authority")))
                    .toList();

            AuthenticatedUser authenticatedUser =
                    new AuthenticatedUser("0", username, null, authorities);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(authenticatedUser, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(auth);
            chain.doFilter(request, response);

        } catch (JwtException e) {
            ErrorModel error = ErrorModel.builder()
                    .mensaje(INVALID_TOKEN.getMessage())
                    .error(e.getMessage())
                    .build();

            ResponseAuthorizationUtil.write(response, error, HttpStatus.FORBIDDEN.value());
        }
    }
}
