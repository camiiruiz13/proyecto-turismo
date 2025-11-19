package com.turismo.show.backend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/*@ComponentScan(basePackages = {
		"com.turismo.show.backend.infrastructure.adapters",
		"com.turismo.show.backend.infrastructure.entrypoints",
		"com.turismo.show.backend.application"
})*/
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
