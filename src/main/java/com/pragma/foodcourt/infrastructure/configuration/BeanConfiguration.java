package com.pragma.foodcourt.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.pragma.foodcourt.domain.api.IRestaurantServicePort;
import com.pragma.foodcourt.domain.clients.IUserRoleValidator;
import com.pragma.foodcourt.domain.spi.IDomainNotificationPort;
import com.pragma.foodcourt.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourt.domain.usecase.CreateRestaurantUseCase;
import com.pragma.foodcourt.infrastructure.exception.DomainNotificationAdapter;
import com.pragma.foodcourt.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.foodcourt.infrastructure.out.jpa.adapter.UserRoleValidatorAdapter;
import com.pragma.foodcourt.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.IRestaurantRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

	private final IRestaurantRepository restaurantRepository;
	private final IRestaurantEntityMapper restaurantEntityMapper;

	private @Value("${users.service-url}") String userServiceUrl;

	@Bean
	public IRestaurantPersistencePort restaurantPersistencePort() {
		return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
	}

	@Bean
	public IRestaurantServicePort restaurantServicePort() {
		return new CreateRestaurantUseCase(restaurantPersistencePort(),
				userRoleValidator(restTemplate(), userServiceUrl), domainNotificationPort());
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public IUserRoleValidator userRoleValidator(RestTemplate restTemplate,
			@Value("${users.service-url}") String userServiceUrl) {
		return new UserRoleValidatorAdapter(restTemplate, userServiceUrl);
	}

	@Bean
	public IDomainNotificationPort domainNotificationPort() {
		return new DomainNotificationAdapter();
	}
}