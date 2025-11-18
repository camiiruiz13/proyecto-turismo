package com.turismo.show.backend.infrastructure.adapters.security.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@ToString
@AllArgsConstructor
public class AuthenticatedUser {

    private final String idUser;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

}
