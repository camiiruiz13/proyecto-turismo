package com.appturismo.show.backend.application.config;

import com.appturismo.show.backend.domain.model.Pais;
import com.appturismo.show.backend.domain.model.TipoDocumento;
import com.appturismo.show.backend.domain.model.gateway.*;
import com.appturismo.show.backend.domain.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsesCasesConfig {

    @Bean
    TipoDocumentoUseCase tipoDocumentoUseCase(TipoDocumentoGateway tipoDocumentoGateway, CacheGateway<TipoDocumento> cacheGateway){
        return new TipoDocumentoUseCase( tipoDocumentoGateway, cacheGateway);
    }

    @Bean
    ListCacheKeysUseCase listCacheKeysUseCase(CacheGateway cacheGateway){
        return new ListCacheKeysUseCase(cacheGateway);
    }

    @Bean
    PaisUseCase paisUseCase(PaisGateway paisGateway, CacheGateway<Pais> cacheGateway){
        return new PaisUseCase( paisGateway, cacheGateway);
    }

    @Bean
    PaisUseCaseByAtribute paisUseCaseByAtribute(PaisGateway paisGateway, CacheGateway<Pais> cacheGateway){
        return new PaisUseCaseByAtribute( paisGateway, cacheGateway);
    }

    @Bean
    TipoDocumentoUseCaseByAtribute tipoDocumentoByAtribute(TipoDocumentoGateway tipoDocumentoGateway, CacheGateway<TipoDocumento> cacheGateway){
        return new TipoDocumentoUseCaseByAtribute( tipoDocumentoGateway, cacheGateway);
    }

    @Bean
    LoginUseCase loginUseCase(UserGateway userGateway,
                              PasswordGateway passwordGateway,
                              AuthenticationGateway authenticationGateway) {
        return new LoginUseCase(userGateway, passwordGateway, authenticationGateway);
    }
}
