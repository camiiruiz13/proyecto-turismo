package com.turismo.show.backend.infrastructure.adapters.securityadapter.handler;


import com.turismo.show.backend.infrastructure.adapters.securityadapter.model.ErrorModel;
import com.turismo.show.backend.infrastructure.adapters.securityadapter.util.ResponseAuthorizationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.ErrorConstants.INVALID_TOKEN;


@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorModel errorResponse  = ErrorModel.builder()
                .message(INVALID_TOKEN.getMessage())
                .error(authException.getMessage())
                .build();

        ResponseAuthorizationUtil.write(response, errorResponse, HttpStatus.UNAUTHORIZED.value());
    }
}
