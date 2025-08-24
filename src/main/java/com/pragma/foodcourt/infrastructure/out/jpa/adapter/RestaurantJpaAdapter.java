package com.pragma.foodcourt.infrastructure.out.jpa.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pragma.foodcourt.domain.model.Restaurant;
import com.pragma.foodcourt.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.IRestaurantRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

	private final IRestaurantRepository restaurantRepository;
	private final IRestaurantEntityMapper restaurantEntityMapper;

	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		RestaurantEntity entity = restaurantEntityMapper.toEntity(restaurant);
		RestaurantEntity saved = restaurantRepository.save(entity);
		return restaurantEntityMapper.toRestaurant(saved);
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		List<RestaurantEntity> entities = restaurantRepository.findAll();
		return restaurantEntityMapper.toRestaurantList(entities);
	}
}