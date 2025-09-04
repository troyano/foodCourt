package com.pragma.foodcourt.domain.api;

import com.pragma.foodcourt.domain.model.Dish;
import com.pragma.foodcourt.domain.model.DishUpdate;

public interface IUpdateDishServicePort {
	void updateDishPriceAndDescription(DishUpdate dish);

    void enableOrDisableDish(int id, boolean active);
}
