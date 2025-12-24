package com.appturismo.show.backend.domain.model.gateway;

public interface PasswordGateway {
    Boolean esClaveValida(String password, String passwordBD);
}
