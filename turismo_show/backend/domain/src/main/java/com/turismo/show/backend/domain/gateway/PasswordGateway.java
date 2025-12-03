package com.turismo.show.backend.domain.gateway;

public interface PasswordGateway {
    Boolean esClaveValida(String password, String passwordBD);
}
