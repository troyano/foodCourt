package com.pragma.foodcourt.domain.usecase;

import java.util.List;

import com.pragma.foodcourt.domain.api.IRestaurantServicePort;
import com.pragma.foodcourt.domain.clients.IUserRoleValidator;
import com.pragma.foodcourt.domain.model.Restaurant;
import com.pragma.foodcourt.domain.spi.IDomainNotificationPort;
import com.pragma.foodcourt.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourt.domain.util.Constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateRestaurantUseCase implements IRestaurantServicePort {
	private final IRestaurantPersistencePort restaurantPersistencePort;
	private final IUserRoleValidator userRoleValidator;
	private final IDomainNotificationPort domainNotificationPort;

	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		if (!restaurant.getTaxId().matches(Constants.REGEX_TAX_ID)) {
			domainNotificationPort.notifyError(Constants.MSG_INVALID_TAX_ID);
		}

		if (!restaurant.getPhone().matches(Constants.REGEX_CELL_PHONE)) {
			domainNotificationPort.notifyError(Constants.MSG_INVALID_CELL_PHONE);
		}

		if (!userRoleValidator.isOwner(restaurant.getCreatedBy())) {
			domainNotificationPort.notifyError(Constants.MSG_USER_NOT_AUTORIZED_AS_OWNER);
		}
		return restaurantPersistencePort.createRestaurant(restaurant);
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantPersistencePort.getAllRestaurants();
	}
}