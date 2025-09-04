package com.pragma.foodcourt.domain.usecase;

import java.util.List;

import com.pragma.foodcourt.domain.api.IRestaurantServicePort;
import com.pragma.foodcourt.domain.api.Page;
import com.pragma.foodcourt.domain.clients.IUserRoleValidator;
import com.pragma.foodcourt.domain.exception.ValidationUtils;
import com.pragma.foodcourt.domain.model.Restaurant;
import com.pragma.foodcourt.domain.model.RestaurantList;
import com.pragma.foodcourt.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourt.domain.util.Constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateRestaurantUseCase implements IRestaurantServicePort {
	private final IRestaurantPersistencePort restaurantPersistencePort;
	private final IUserRoleValidator userRoleValidator;

	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		validateBasicData(restaurant);
		ValidationUtils.validateUserRoleOrThrow(
				userRoleValidator.isRole(restaurant.getCreatedBy(), Constants.ROLE_ADM),
				Constants.MSG_USER_NOT_AUTHORIZED_AS_ADM);
		return restaurantPersistencePort.createRestaurant(restaurant);
	}

	@Override
	public Page<RestaurantList> listRestaurants(int page, int size) {
		return restaurantPersistencePort.listAllRestaurants(page, size);
	}

	private void validateBasicData(Restaurant restaurant) {
		ValidationUtils.validatePhone(restaurant.getPhone());
		ValidationUtils.validateTaxId(restaurant.getTaxId());
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantPersistencePort.getAllRestaurants();
	}
}
