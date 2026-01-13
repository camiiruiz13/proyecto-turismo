package com.appturismo.show.backend.domain.model.gateway;


import com.appturismo.show.backend.domain.model.User;

public interface UserGateway {

    User findByCorreo(String correo);
    User createUser(User user);
}
