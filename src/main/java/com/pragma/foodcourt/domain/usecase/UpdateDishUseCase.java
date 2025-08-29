package com.pragma.foodcourt.domain.usecase;

import com.pragma.foodcourt.domain.api.IDishServicePort;
import com.pragma.foodcourt.domain.api.IUpdateDishServicePort;
import com.pragma.foodcourt.domain.clients.IUserRoleValidator;
import com.pragma.foodcourt.domain.exception.ResourceNotFoundException;
import com.pragma.foodcourt.domain.exception.ValidationUtils;
import com.pragma.foodcourt.domain.model.Dish;
import com.pragma.foodcourt.domain.model.DishUpdate;
import com.pragma.foodcourt.domain.spi.IDishPersistencePort;
import com.pragma.foodcourt.domain.util.Constants;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.ICategoryRepository;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.IDishRepository;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateDishUseCase implements IUpdateDishServicePort {
	private final IDishPersistencePort dishPersistencePort;
	private final IUserRoleValidator userRoleValidator;
	private final IDishRepository dishRepository;

	@Override
	public void updateDishPriceAndDescription(DishUpdate dish) {
		ValidationUtils.validateUserRoleOrThrow(userRoleValidator.isRole(dish.getCreatedBy(), Constants.ROLE_OWNER),
				Constants.MSG_USER_NOT_AUTHORIZED_AS_OWNER);
		ValidationUtils.validateDishPrice(dish.getPrice());
		ValidationUtils.validateRequiredString(dish.getDescription(), Constants.MSG_DESCRIPTION_REQUIRED);
		Optional<DishEntity> dishOpt = dishRepository.findById(dish.getId());
		if (dishOpt.isPresent()) {
			dishOpt.get().setPrice(dish.getPrice());
			dishOpt.get().setDescription(dish.getDescription());
			dishPersistencePort.updateDishPriceAndDescription(dishOpt.get());
		} else {
			throw new ResourceNotFoundException(Constants.MSG_DISH_NOT_FOUND);
		}
	}
}
