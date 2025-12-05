package com.turismo.show.backend.infrastructure.entrypoints.restapi.advices;

import com.turismo.show.backend.domain.exception.TipoDocumentoNotFoundException;
import com.turismo.show.backend.domain.exception.UserNotFoundException;
import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.exception.InfrastructureDatabaseException;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.util.ResponseUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.http.HttpStatus;

@Component
public class GlobalErrorFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {


    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction<ServerResponse> next) throws Exception {
        try {
            return next.handle(request);
        }catch (UserNotFoundException ex){
            return ServerResponse.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.buildErrorResponse(ex.getErrorMessage(), ex.getMessage()));
        }catch (TipoDocumentoNotFoundException ex){
            return ServerResponse.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.buildErrorResponse(ex.getErrorMessage(), ex.getMessage()));
        }catch (InfrastructureDatabaseException ex){
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.buildErrorResponse(ex.getErrorMessage(), ex.getMessage()));
        }
    }
}
