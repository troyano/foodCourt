package com.pragma.foodcourt.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pragma.foodcourt.domain.clients.IUserRoleValidator;
import com.pragma.foodcourt.domain.model.Dish;
import com.pragma.foodcourt.domain.spi.IDishPersistencePort;
import com.pragma.foodcourt.domain.spi.IDomainNotificationPort;
import com.pragma.foodcourt.domain.util.Constants;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.ICategoryRepository;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.IRestaurantRepository;

class CreateDishUseCaseTest {
	@Mock
	private IDishPersistencePort dishPersistencePort;
	@Mock
	private IUserRoleValidator userRoleValidator;
	@Mock
	private IDomainNotificationPort domainNotificationPort;
	@Mock
	private IRestaurantRepository restaurantRepository;
	@Mock
	private ICategoryRepository categoryRepository;
	@InjectMocks
	private CreateDishUseCase createDishUseCase;

	private Dish validDish;
	private RestaurantEntity restaurantEntity;
	private CategoryEntity categoryEntity;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		validDish = new Dish();
		validDish.setCreatedBy("owner");
		validDish.setRestaurantTaxId("1234567890");
		validDish.setCategoryCode("CAT01");
		restaurantEntity = new RestaurantEntity();
		categoryEntity = new CategoryEntity();
	}

	@Test
	void createDish_validData_success() {
		when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(true);
		when(restaurantRepository.findByTaxId(anyString())).thenReturn(Optional.of(restaurantEntity));
		when(categoryRepository.findByCode(anyString())).thenReturn(Optional.of(categoryEntity));
		when(dishPersistencePort.createDish(any(Dish.class), any(RestaurantEntity.class), any(CategoryEntity.class)))
				.thenReturn(validDish);
		Dish result = createDishUseCase.createDish(validDish);
		assertEquals(validDish, result);
		verify(domainNotificationPort, never()).notifyError(anyString());
	}

	@Test
	void createDish_userNotOwner_triggersNotification() {
		when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(false);
		Dish result = createDishUseCase.createDish(validDish);
		verify(domainNotificationPort).notifyError(Constants.MSG_USER_NOT_AUTORIZED_AS_OWNER);
		assertNull(result);
	}

	@Test
	void createDish_restaurantNotFound_triggersNotification() {
		when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(true);
		when(restaurantRepository.findByTaxId(anyString())).thenReturn(Optional.empty());
		when(categoryRepository.findByCode(anyString())).thenReturn(Optional.of(categoryEntity));
		Dish result = createDishUseCase.createDish(validDish);
		verify(domainNotificationPort).notifyError(Constants.MSG_RESTAURANT_NOT_FOUNT);
		assertNull(result);
	}

	@Test
	void createDish_categoryNotFound_triggersNotification() {
		when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(true);
		when(restaurantRepository.findByTaxId(anyString())).thenReturn(Optional.of(restaurantEntity));
		when(categoryRepository.findByCode(anyString())).thenReturn(Optional.empty());
		Dish result = createDishUseCase.createDish(validDish);
		verify(domainNotificationPort).notifyError(Constants.MSG_CATEGORY_NOT_FOUND);
		assertNull(result);
	}
}
