package com.pragma.foodcourt.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pragma.foodcourt.domain.clients.IUserRoleValidator;
import com.pragma.foodcourt.domain.model.Restaurant;
import com.pragma.foodcourt.domain.spi.IDomainNotificationPort;
import com.pragma.foodcourt.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourt.domain.util.Constants;

class CreateRestaurantUseCaseTest {
	@Mock
	private IRestaurantPersistencePort restaurantPersistencePort;
	@Mock
	private IUserRoleValidator userRoleValidator;
	@Mock
	private IDomainNotificationPort domainNotificationPort;
	@InjectMocks
	private CreateRestaurantUseCase createRestaurantUseCase;

	private Restaurant validRestaurant;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		validRestaurant = new Restaurant();
		validRestaurant.setTaxId("1234567890");
		validRestaurant.setPhone("3001234567");
		validRestaurant.setCreatedBy("user");
	}

	@Test
	void createRestaurant_validData_success() {
		when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(true);
		when(restaurantPersistencePort.createRestaurant(any(Restaurant.class))).thenReturn(validRestaurant);
		Restaurant result = createRestaurantUseCase.createRestaurant(validRestaurant);
		assertEquals(validRestaurant, result);
		verify(domainNotificationPort, never()).notifyError(anyString());
	}

	@Test
	void createRestaurant_invalidTaxId_triggersNotification() {
		validRestaurant.setTaxId("invalid");
		when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(true);
		when(restaurantPersistencePort.createRestaurant(any(Restaurant.class))).thenReturn(validRestaurant);
		createRestaurantUseCase.createRestaurant(validRestaurant);
		verify(domainNotificationPort).notifyError(Constants.MSG_INVALID_TAX_ID);
	}

	@Test
	void createRestaurant_invalidPhone_triggersNotification() {
		validRestaurant.setPhone("123-456");
		when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(true);
		when(restaurantPersistencePort.createRestaurant(any(Restaurant.class))).thenReturn(validRestaurant);
		createRestaurantUseCase.createRestaurant(validRestaurant);
		verify(domainNotificationPort).notifyError(Constants.MSG_INVALID_CELL_PHONE);
		verify(restaurantPersistencePort).createRestaurant(validRestaurant);
	}

	@Test
	void createRestaurant_userNotOwner_triggersNotification() {
		when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(false);
		when(restaurantPersistencePort.createRestaurant(any(Restaurant.class))).thenReturn(validRestaurant);
		createRestaurantUseCase.createRestaurant(validRestaurant);
		verify(domainNotificationPort).notifyError(Constants.MSG_USER_NOT_AUTORIZED_AS_OWNER);
		verify(restaurantPersistencePort).createRestaurant(validRestaurant);
	}

	@Test
	void getAllRestaurants_delegatesToPersistencePort() {
		List<Restaurant> restaurants = List.of(validRestaurant);
		when(restaurantPersistencePort.getAllRestaurants()).thenReturn(restaurants);
		List<Restaurant> result = createRestaurantUseCase.getAllRestaurants();
		assertEquals(restaurants, result);
		verify(restaurantPersistencePort).getAllRestaurants();
	}
}