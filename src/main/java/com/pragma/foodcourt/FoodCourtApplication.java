package com.pragma.foodcourt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FoodCourtApplication {

	public static void main(final String[] args) {
		SpringApplication.run(FoodCourtApplication.class, args);
	}

}
