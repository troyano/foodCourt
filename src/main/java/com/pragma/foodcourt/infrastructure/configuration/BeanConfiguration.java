package com.pragma.foodcourt.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pragma.foodcourt.domain.api.IObjectServicePort;
import com.pragma.foodcourt.domain.api.IRestaurantServicePort;
import com.pragma.foodcourt.domain.clients.IUserRoleValidator;
import com.pragma.foodcourt.domain.spi.IObjectPersistencePort;
import com.pragma.foodcourt.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourt.domain.usecase.CreateRestaurantUseCase;
import com.pragma.foodcourt.domain.usecase.ObjectUseCase;
import com.pragma.foodcourt.infrastructure.out.jpa.adapter.ObjectJpaAdapter;
import com.pragma.foodcourt.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.foodcourt.infrastructure.out.jpa.adapter.UserRoleValidatorAdapter;
import com.pragma.foodcourt.infrastructure.out.jpa.mapper.IObjectEntityMapper;
import com.pragma.foodcourt.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.IObjectRepository;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.IRestaurantRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
	private final IObjectRepository objectRepository;
	private final IObjectEntityMapper objectEntityMapper;
	private final IRestaurantRepository restaurantRepository;
	private final IRestaurantEntityMapper restaurantEntityMapper;

	@Bean
	public IObjectPersistencePort objectPersistencePort() {
		return new ObjectJpaAdapter(objectRepository, objectEntityMapper);
	}

	@Bean
	public IObjectServicePort objectServicePort() {
		return new ObjectUseCase(objectPersistencePort());
	}


	@Bean
	public IRestaurantPersistencePort restaurantPersistencePort() {
		return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
	}

	@Bean
	public IRestaurantServicePort restaurantServicePort() {
		return new CreateRestaurantUseCase(restaurantPersistencePort(), userRoleValidator());
	}

	@Bean
	public IUserRoleValidator userRoleValidator() {
		return new UserRoleValidatorAdapter();
	}
}