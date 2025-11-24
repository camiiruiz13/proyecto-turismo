package com.turismo.show.backend.infrastructure.entrypoints.restapi.advices;


import com.turismo.show.backend.infrastructure.adapters.jpapostgresqladapter.exception.InfrastructureDatabaseException;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.ErrorResponseDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.util.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(InfrastructureDatabaseException.class)
    public ResponseEntity<ErrorResponseDTO> handleDatabaseException(InfrastructureDatabaseException ex) {
       return new ResponseEntity<>(ResponseUtils.buildErrorResponse(ex.getErrorMessage(), ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
