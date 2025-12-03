package com.turismo.show.backend.infrastructure.adapters.securityadapter.util;


import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

import static com.turismo.show.backend.infrastructure.adapters.securityadapter.commons.TokenJwtConfig.CONTENT_TYPE;


@UtilityClass
public class ResponseAuthorizationUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void write(HttpServletResponse response, Object body, int status) throws IOException {
        response.setStatus(status);
        response.setContentType(CONTENT_TYPE);
        response.getWriter().write(mapper.writeValueAsString(body));

    }
}
