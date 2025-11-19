package com.turismo.show.backend.infrastructure.adapters.security;

import com.turismo.show.backend.domain.gateway.AuthenticationGateway;
import com.turismo.show.backend.domain.model.User;
import com.turismo.show.backend.infrastructure.adapters.security.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationGatewayAdapter implements AuthenticationGateway {
    private final AuthenticationFilter authenticationFilter;

    @Override
    public String generateToken(User user) {
        Authentication auth = authenticationFilter.authenticateUser(user);
        Map<String, Object> response = authenticationFilter.generateTokenResponse(auth);

        return response.get("token").toString();
    }
}
