package com.appturismo.show.backend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.appturismo.show.backend")
@EnableJpaRepositories(basePackages = "com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.repositories")
@EntityScan(basePackages = "com.appturismo.show.backend.infrastructure.driverapaters.oracleadapter.entities")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
