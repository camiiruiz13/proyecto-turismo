package com.appturismo.show.backend.domain.model.gateway;


import com.appturismo.show.backend.domain.model.User;

public interface AuthenticationGateway {

    String generateToken(User user);
}
