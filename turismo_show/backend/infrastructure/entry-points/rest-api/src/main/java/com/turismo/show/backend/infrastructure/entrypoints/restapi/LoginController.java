package com.turismo.show.backend.infrastructure.entrypoints.restapi;


import com.turismo.show.backend.domain.usecase.LoginUseCase;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.commons.properties.ApiPathsProperties;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.request.LoginDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.AuthResponseDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.ErrorResponseDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.SuccesResponseDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.mapper.LoginResponseMapper;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.util.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.turismo.show.backend.infrastructure.entrypoints.restapi.commons.ResponseMessages.SESSION_SUCCES;
import static com.turismo.show.backend.infrastructure.entrypoints.restapi.commons.SwaggerConstants.*;

@Tag(name = TAG_NAME_LOGIN, description = TAG_DESCRIPTION_LOGIN)
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.base-path}")
public class LoginController {

    private final LoginUseCase loginUseCase;
    private final ApiPathsProperties apiPathsProperties;
    private final LoginResponseMapper loginResponseMapper;

    @Operation(summary = LOGIN_SUMMARY, description = LOGIN_DESCRIPTION, security = @SecurityRequirement(name = ""))
    @ApiResponses(value = {
            @ApiResponse(responseCode = HTTP_200, description = LOGIN_SUCCESS,
                    content = @Content(schema = @Schema(implementation = SuccesResponseDTO.class))),
            @ApiResponse(responseCode = HTTP_400, description = LOGIN_INVALID_DATA,
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = HTTP_401, description = LOGIN_UNAUTHORIZED,
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = HTTP_500, description = LOGIN_INTERNAL_ERROR,
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })

    public ResponseEntity<SuccesResponseDTO> login(  @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = DESCRIPTION_REQUEST,
            content = @Content(
                    mediaType = CONTENT_TYPE,
                    schema = @Schema(implementation = LoginDTO.class)
            )
    ) @RequestBody LoginDTO request) {
        AuthResponseDTO authResponseDTO = loginResponseMapper.toResponse(loginUseCase.login(request.getCorreo(), request.getPassword()));
        return ResponseEntity.ok(ResponseUtils.buildSuccessResponse(SESSION_SUCCES.getMessage(), authResponseDTO));
    }

}
