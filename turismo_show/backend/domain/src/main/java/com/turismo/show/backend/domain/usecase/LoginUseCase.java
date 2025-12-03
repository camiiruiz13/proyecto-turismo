package com.turismo.show.backend.domain.usecase;

import com.turismo.show.backend.domain.exception.UserNotFoundException;
import com.turismo.show.backend.domain.gateway.AuthenticationGateway;
import com.turismo.show.backend.domain.gateway.PasswordGateway;
import com.turismo.show.backend.domain.gateway.UserGateway;
import com.turismo.show.backend.domain.model.User;
import com.turismo.show.backend.domain.model.login.Login;
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
