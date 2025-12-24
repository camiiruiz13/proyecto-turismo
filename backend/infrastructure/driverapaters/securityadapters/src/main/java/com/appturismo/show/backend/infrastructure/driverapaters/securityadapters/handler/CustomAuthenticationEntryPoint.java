package com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.handler;


import com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.model.ErrorModel;
import com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.util.ResponseAuthorizationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.appturismo.show.backend.infrastructure.driverapaters.securityadapters.commons.ErrorConstants.INVALID_TOKEN;

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
