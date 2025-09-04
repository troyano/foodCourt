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

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public Optional<Dish> findById(long id) {
        return dishRepository.findById(id).map(dishEntityMapper::toDish);
    }

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
    public void updateDishPriceAndDescription(Dish dish) {
        DishEntity dishEntity = dishEntityMapper.toEntity(dish);
        dishRepository.save(dishEntity);
    }

    @Override
    public void enableOrDisableDish(Dish dish) {
        DishEntity dishEntity = dishEntityMapper.toEntity(dish);
        dishRepository.save(dishEntity);
    }
}
