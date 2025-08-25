package com.pragma.foodcourt.domain.usecase;

import java.util.Optional;

import com.pragma.foodcourt.domain.api.IDishServicePort;
import com.pragma.foodcourt.domain.clients.IUserRoleValidator;
import com.pragma.foodcourt.domain.model.Dish;
import com.pragma.foodcourt.domain.spi.IDishPersistencePort;
import com.pragma.foodcourt.domain.spi.IDomainNotificationPort;
import com.pragma.foodcourt.domain.util.Constants;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.ICategoryRepository;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.IRestaurantRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateDishUseCase implements IDishServicePort {
	private final IDishPersistencePort dishPersistencePort;
	private final IUserRoleValidator userRoleValidator;
	private final IDomainNotificationPort domainNotificationPort;
	private final IRestaurantRepository restaurantRepository;
	private final ICategoryRepository categoryRepository;

    @Override
    public Dish createDish(Dish dish) {
		// Only owner can create
		if (!userRoleValidator.isOwner(dish.getCreatedBy())) {
			domainNotificationPort.notifyError(Constants.MSG_USER_NOT_AUTORIZED_AS_OWNER);
		}
		// Find restaurant by taxId
		Optional<RestaurantEntity> restaurantOpt = restaurantRepository
				.findByTaxId(dish.getRestaurantTaxId());
		if (restaurantOpt.isEmpty()) {
			domainNotificationPort.notifyError(Constants.MSG_RESTAURANT_NOT_FOUNT);
		}
		// Find category by code
		Optional<CategoryEntity> categoryOpt = categoryRepository.findByCode(dish.getCategoryCode());
		if (categoryOpt.isEmpty()) {
			domainNotificationPort.notifyError(Constants.MSG_CATEGORY_NOT_FOUND);
		}
        if (restaurantOpt.isPresent() && categoryOpt.isPresent()) {
            return dishPersistencePort.createDish(dish, restaurantOpt.get(), categoryOpt.get());
        }
        return null;
    }
}
