package com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.advices;

import com.appturismo.show.backend.domain.model.exception.InvalidCountryNameException;
import com.appturismo.show.backend.domain.model.exception.PersonaAlreadyExistsException;
import com.appturismo.show.backend.domain.model.exception.TipoDocumentoNotFoundException;
import com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.exception.InfrastructureDatabaseException;
import com.appturismo.show.backend.infrastructure.driverapaters.redisdapter.exception.CacheNoEncontradaException;
import com.appturismo.show.backend.infrastructure.driverapaters.redisdapter.exception.CacheParametricaException;
import com.appturismo.show.backend.infrastructure.entrypoints.entrypoints.restapi.util.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
public class GlobalErrorFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {
    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction<ServerResponse> next) throws Exception {
        try {
            return next.handle(request);
        }catch (InvalidCountryNameException ex){
            return ServerResponse.status(HttpStatus.BAD_REQUEST)
                    .body(ResponseUtils.buildErrorResponse(ex.getErrorMessage(), ex.getMessage()));
        }catch (TipoDocumentoNotFoundException ex){
            return ServerResponse.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.buildErrorResponse(ex.getErrorMessage(), ex.getMessage()));
        }catch (InfrastructureDatabaseException ex){
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.buildErrorResponse(ex.getErrorMessage(), ex.getMessage()));
        }catch (CacheNoEncontradaException ex){
            return ServerResponse.status(HttpStatus.NOT_FOUND)
                    .body(ResponseUtils.buildErrorResponse(ex.getCode(), ex.getMessage()));

        } catch (CacheParametricaException ex){
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseUtils.buildErrorResponse(ex.getCode(), ex.getMessage()));
        }catch (PersonaAlreadyExistsException ex){
            return ServerResponse.status(HttpStatus.CONFLICT)
                    .body(ResponseUtils.buildErrorResponse(ex.getErrorMessage(), ex.getMessage()));
        }
    }
}
