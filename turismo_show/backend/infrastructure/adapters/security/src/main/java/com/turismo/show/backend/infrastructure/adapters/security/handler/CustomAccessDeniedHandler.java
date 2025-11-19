package com.turismo.show.backend.infrastructure.adapters.security.handler;

import com.turismo.show.backend.infrastructure.adapters.security.model.ErrorModel;
import com.turismo.show.backend.infrastructure.adapters.security.util.ResponseAuthorizationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.turismo.show.backend.infrastructure.adapters.security.commons.ErrorConstants.ACCES_DENIED;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
            ErrorModel errorResponse  = ErrorModel.builder()
                    .ruta(request.getRequestURI())
                    .error(ACCES_DENIED.getMessage())
                    .build();

        ResponseAuthorizationUtil.write(response, errorResponse, HttpServletResponse.SC_FORBIDDEN);
    }
}
