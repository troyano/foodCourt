package com.pragma.foodcourt.infrastructure.out.jpa.adapter;

import com.pragma.foodcourt.domain.model.Dish;
import com.pragma.foodcourt.domain.model.DishUpdate;
import com.pragma.foodcourt.domain.spi.IDishPersistencePort;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

	@Override
	public Dish createDish(Dish dish, RestaurantEntity restaurantEntity, CategoryEntity categoryEntity) {
		DishEntity entity = dishEntityMapper.toEntity(dish);
		// TODO probar el id dentro de la entidad para que lo asuma el mapper
		entity.setRestaurant(restaurantEntity);
		entity.setCategory(categoryEntity);
		DishEntity saved = dishRepository.save(entity);
		return dishEntityMapper.toModel(saved);
	}

	@Override
	public void updateDishPriceAndDescription(DishEntity dish) {
		dishRepository.save(dish);
	}
}