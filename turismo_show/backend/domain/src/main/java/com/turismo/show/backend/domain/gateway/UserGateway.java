package com.turismo.show.backend.domain.gateway;

import com.turismo.show.backend.domain.model.User;

public interface UserGateway {

    User findByCorreo(String correo);
}
