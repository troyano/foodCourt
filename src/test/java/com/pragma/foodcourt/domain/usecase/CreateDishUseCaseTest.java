package com.pragma.foodcourt.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import com.pragma.foodcourt.domain.exception.ResourceNotFoundException;
import com.pragma.foodcourt.domain.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pragma.foodcourt.domain.clients.IUserRoleValidator;
import com.pragma.foodcourt.domain.model.Dish;
import com.pragma.foodcourt.domain.spi.IDishPersistencePort;
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
		validDish.setPrice(new BigDecimal("10"));
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
		verify(dishPersistencePort).createDish(validDish, restaurantEntity, categoryEntity);
	}

	@Test
	void createDish_userNotOwner_throwsValidationException() {
		when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(false);

		ValidationException exception = assertThrows(ValidationException.class, () -> {
			createDishUseCase.createDish(validDish);
		});

		assertEquals(Constants.MSG_USER_NOT_AUTHORIZED_AS_OWNER, exception.getMessage());
	}

	@Test
	void createDish_restaurantNotFound_throwsValidationException() {
		when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(true);
		when(restaurantRepository.findByTaxId(anyString())).thenReturn(Optional.empty());

		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
			createDishUseCase.createDish(validDish);
		});

		assertEquals(Constants.MSG_RESTAURANT_NOT_FOUNT, exception.getMessage());
	}

	@Test
	void createDish_categoryNotFound_throwsValidationException() {
		when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(true);
		when(restaurantRepository.findByTaxId(anyString())).thenReturn(Optional.of(restaurantEntity));
		when(categoryRepository.findByCode(anyString())).thenReturn(Optional.empty());

		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
			createDishUseCase.createDish(validDish);
		});

		assertEquals(Constants.MSG_CATEGORY_NOT_FOUND, exception.getMessage());
	}
}
