package com.turismo.show.backend.infrastructure.entrypoints.restapi.handler;

import com.turismo.show.backend.domain.usecase.LoginUseCase;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.request.LoginDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.dto.response.AuthResponseDTO;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.mapper.LoginResponseMapper;
import com.turismo.show.backend.infrastructure.entrypoints.restapi.util.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import static com.turismo.show.backend.infrastructure.entrypoints.restapi.commons.ResponseMessages.SESSION_SUCCES;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginHandler {

    private final LoginUseCase loginUseCase;
    private final LoginResponseMapper loginResponseMapper;

    public ServerResponse handleLogin(ServerRequest request) throws Exception {
        LoginDTO requestDTO = request.body(LoginDTO.class);
        AuthResponseDTO auth = loginResponseMapper.toResponse(
                loginUseCase.login(requestDTO.getCorreo(), requestDTO.getPassword())
        );

        return ServerResponse.ok().body(ResponseUtils.buildSuccessResponse(SESSION_SUCCES.getMessage(), auth));

    }
}
