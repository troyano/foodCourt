package com.pragma.foodcourt.infrastructure.configuration;

import com.pragma.foodcourt.domain.api.IUpdateDishServicePort;
import com.pragma.foodcourt.domain.usecase.UpdateDishUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.pragma.foodcourt.domain.api.IDishServicePort;
import com.pragma.foodcourt.domain.api.IRestaurantServicePort;
import com.pragma.foodcourt.domain.clients.IUserRoleValidator;
import com.pragma.foodcourt.domain.spi.IDishPersistencePort;
import com.pragma.foodcourt.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourt.domain.usecase.CreateDishUseCase;
import com.pragma.foodcourt.domain.usecase.CreateRestaurantUseCase;
import com.pragma.foodcourt.infrastructure.out.jpa.adapter.DishJpaAdapter;
import com.pragma.foodcourt.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.foodcourt.infrastructure.out.jpa.adapter.UserRoleValidatorAdapter;
import com.pragma.foodcourt.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.foodcourt.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.ICategoryRepository;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.IDishRepository;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.IRestaurantRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

	private final IRestaurantRepository restaurantRepository;
	private final IRestaurantEntityMapper restaurantEntityMapper;
	private final IDishRepository dishRepository;
	private final IDishEntityMapper dishEntityMapper;
	private final ICategoryRepository categoryRepository;

	private @Value("${users.service-url}") String userServiceUrl;

	@Bean
	public IRestaurantPersistencePort restaurantPersistencePort() {
		return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
	}

	@Bean
	public IRestaurantServicePort restaurantServicePort() {
		return new CreateRestaurantUseCase(restaurantPersistencePort(),
				userRoleValidator(restTemplate(), userServiceUrl));
	}

	@Bean
	public IDishPersistencePort dishPersistencePort() {
		return new DishJpaAdapter(dishRepository, dishEntityMapper);
	}

	@Bean
	public IDishServicePort dishServicePort() {
		return new CreateDishUseCase(dishPersistencePort(), userRoleValidator(restTemplate(), userServiceUrl), restaurantRepository, categoryRepository);
	}

	@Bean
	public IUpdateDishServicePort updateDishServicePort() {
		return new UpdateDishUseCase(dishPersistencePort(), userRoleValidator(restTemplate(), userServiceUrl), dishRepository);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public IUserRoleValidator userRoleValidator(RestTemplate restTemplate, String userServiceUrl) {
		return new UserRoleValidatorAdapter(restTemplate, userServiceUrl);
	}
}