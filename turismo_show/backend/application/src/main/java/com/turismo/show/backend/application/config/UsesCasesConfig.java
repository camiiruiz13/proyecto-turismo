package com.turismo.show.backend.application.config;


import com.turismo.show.backend.domain.gateway.AuthenticationGateway;
import com.turismo.show.backend.domain.gateway.PasswordGateway;
import com.turismo.show.backend.domain.gateway.TipoDocumentoGateway;
import com.turismo.show.backend.domain.gateway.UserGateway;
import com.turismo.show.backend.domain.usecase.LoginUseCase;
import com.turismo.show.backend.domain.usecase.TipoDocumentoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsesCasesConfig {


    @Bean
    LoginUseCase loginUseCase(UserGateway userGateway,
                                     PasswordGateway passwordGateway,
                                     AuthenticationGateway authenticationGateway) {
        return new LoginUseCase(userGateway, passwordGateway, authenticationGateway);
    }

    @Bean
    TipoDocumentoUseCase tipoDocumentoUseCase(TipoDocumentoGateway tipoDocumentoGateway) {
        return new TipoDocumentoUseCase(tipoDocumentoGateway);
    }



}
