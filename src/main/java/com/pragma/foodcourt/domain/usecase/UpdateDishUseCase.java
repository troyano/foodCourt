package com.pragma.foodcourt.domain.usecase;

import com.pragma.foodcourt.domain.api.IUpdateDishServicePort;
import com.pragma.foodcourt.domain.clients.IUserRoleValidator;
import com.pragma.foodcourt.domain.exception.NotRestaurantOwnerException;
import com.pragma.foodcourt.domain.exception.RestaurantNotFoundException;
import com.pragma.foodcourt.domain.exception.ResourceNotFoundException;
import com.pragma.foodcourt.domain.exception.ValidationUtils;
import com.pragma.foodcourt.domain.model.Dish;
import com.pragma.foodcourt.domain.model.DishUpdate;
import com.pragma.foodcourt.domain.spi.IDishPersistencePort;
import com.pragma.foodcourt.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourt.domain.spi.IUserContextProviderPort;
import com.pragma.foodcourt.domain.util.Constants;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UpdateDishUseCase implements IUpdateDishServicePort {
    private final IDishPersistencePort dishPersistencePort;
    private final IUserRoleValidator userRoleValidator;
    private final IUserContextProviderPort userContextProviderPort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    @Override
    public void updateDishPriceAndDescription(DishUpdate dish) {
        ValidationUtils.validateUserRoleOrThrow(userRoleValidator.isRole(dish.getCreatedBy(), Constants.ROLE_OWNER),
                Constants.MSG_USER_NOT_AUTHORIZED_AS_OWNER);
        ValidationUtils.validateDishPrice(dish.getPrice());
        ValidationUtils.validateRequiredString(dish.getDescription(), Constants.MSG_DESCRIPTION_REQUIRED);
        Optional<Dish> dishOpt = dishPersistencePort.findById(dish.getId());
        if (dishOpt.isPresent()) {
            dishOpt.get().setPrice(dish.getPrice());
            dishOpt.get().setDescription(dish.getDescription());
            dishPersistencePort.updateDishPriceAndDescription(dishOpt.get());
        } else {
            throw new ResourceNotFoundException(Constants.MSG_DISH_NOT_FOUND);
        }
    }

    @Override
    public void enableOrDisableDish(int id, boolean active) {
        final String userOwner = userContextProviderPort.getAuthenticatedUser();
        ValidationUtils.validateUserRoleOrThrow(userRoleValidator.isRole(userOwner, Constants.ROLE_OWNER),
                Constants.MSG_USER_NOT_AUTHORIZED_AS_OWNER);
        Optional<Dish> dishOpt = dishPersistencePort.findById((long) id);
        if (dishOpt.isPresent()) {
            String restaurantOwnerUser = restaurantPersistencePort.findOwnerUserById(dishOpt.get().getRestaurantId())
                    .orElseThrow(RestaurantNotFoundException::new);

            if (!restaurantOwnerUser.equals(userOwner)) {
                throw new NotRestaurantOwnerException();
            }
            dishOpt.get().setActive(active);
            dishPersistencePort.enableOrDisableDish(dishOpt.get());
        } else {
            throw new ResourceNotFoundException(Constants.MSG_DISH_NOT_FOUND);
        }
    }
}
