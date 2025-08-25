package com.pragma.foodcourt.domain.api;

import com.pragma.foodcourt.domain.model.Dish;

public interface IDishServicePort {
	Dish createDish(Dish dish);
}
