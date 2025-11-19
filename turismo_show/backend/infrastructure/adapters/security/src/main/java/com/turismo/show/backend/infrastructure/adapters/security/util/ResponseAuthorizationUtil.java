package com.turismo.show.backend.infrastructure.adapters.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import java.io.IOException;

import static com.turismo.show.backend.infrastructure.adapters.security.commons.TokenJwtConfig.CONTENT_TYPE;

@UtilityClass
public class ResponseAuthorizationUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void write(HttpServletResponse response, Object body, int status) throws IOException {
        response.setStatus(status);
        response.setContentType(CONTENT_TYPE);
        response.getWriter().write(mapper.writeValueAsString(body));

    }
}
