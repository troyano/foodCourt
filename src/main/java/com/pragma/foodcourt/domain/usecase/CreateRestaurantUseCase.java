package com.pragma.foodcourt.domain.usecase;

import com.pragma.foodcourt.domain.api.IRestaurantServicePort;
import com.pragma.foodcourt.domain.clients.IUserRoleValidator;
import com.pragma.foodcourt.domain.model.Restaurant;
import com.pragma.foodcourt.domain.spi.IRestaurantPersistencePort;

public class CreateRestaurantUseCase implements IRestaurantServicePort {
	private final IRestaurantPersistencePort restaurantPersistencePort;
	private final IUserRoleValidator userRoleValidator;

	public CreateRestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort,
			IUserRoleValidator userRoleValidator) {
		this.restaurantPersistencePort = restaurantPersistencePort;
		this.userRoleValidator = userRoleValidator;
	}

	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		if (restaurantPersistencePort.existsByTaxId(restaurant.getTaxId())) {
			throw new IllegalArgumentException("A restaurant with this NIT already exists");
		}
		if (!userRoleValidator.isOwner(restaurant.getCreatedBy())) {
			throw new IllegalArgumentException("The user is not authorized as an owner");
		}
		return restaurantPersistencePort.createRestaurant(restaurant);
	}

	@Override
	public java.util.List<Restaurant> getAllRestaurants() {
		return restaurantPersistencePort.getAllRestaurants();
	}
}