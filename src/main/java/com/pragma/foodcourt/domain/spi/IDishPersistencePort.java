package com.pragma.foodcourt.domain.spi;

import com.pragma.foodcourt.domain.model.Dish;
import com.pragma.foodcourt.domain.model.DishUpdate;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.RestaurantEntity;

public interface IDishPersistencePort {
    Dish createDish(Dish dish, RestaurantEntity restaurantEntity, CategoryEntity categoryEntity);

	void updateDishPriceAndDescription(DishEntity dish);
}
