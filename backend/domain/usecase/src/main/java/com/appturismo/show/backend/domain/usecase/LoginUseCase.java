package com.appturismo.show.backend.domain.usecase;

import com.appturismo.show.backend.domain.model.User;
import com.appturismo.show.backend.domain.model.exception.UserNotFoundException;
import com.appturismo.show.backend.domain.model.gateway.AuthenticationGateway;
import com.appturismo.show.backend.domain.model.gateway.PasswordGateway;
import com.appturismo.show.backend.domain.model.gateway.UserGateway;
import com.appturismo.show.backend.domain.model.login.Login;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginUseCase {

    private final UserGateway userGateway;
    private final PasswordGateway passwordGateway;
    private final AuthenticationGateway authenticationGateway;

    public Login login(String username, String password) {
        User user = userGateway.findByCorreo(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }
        passwordGateway.esClaveValida(password, user.getClave());

        return Login.builder()
                .idUsuario(user.getIdUsuario())
                .username(user.getCorreo())
                .rol(user.getRol())
                .foto(user.getFotoBase64())
                .token(authenticationGateway.generateToken(user))
                .build();

    }
}

