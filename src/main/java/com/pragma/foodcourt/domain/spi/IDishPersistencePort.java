package com.pragma.foodcourt.domain.spi;

import com.pragma.foodcourt.domain.model.Dish;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.RestaurantEntity;

import java.util.Optional;

public interface IDishPersistencePort {
    Optional<Dish> findById(long id);
    Dish createDish(Dish dish, RestaurantEntity restaurantEntity, CategoryEntity categoryEntity);

	void updateDishPriceAndDescription(Dish dish);

    void enableOrDisableDish(Dish dishEntity);
}
