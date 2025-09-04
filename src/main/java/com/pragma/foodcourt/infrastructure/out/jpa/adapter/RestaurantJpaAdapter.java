package com.pragma.foodcourt.infrastructure.out.jpa.adapter;

import java.util.List;
import java.util.Optional;

import com.pragma.foodcourt.domain.api.Page;
import com.pragma.foodcourt.domain.model.RestaurantList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@Override
	public Optional<String> findOwnerUserById(Long id) {
		return restaurantRepository.findCreatedBy(id);
	}

	@Override
	public Page<RestaurantList> listAllRestaurants(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
		org.springframework.data.domain.Page<RestaurantEntity> springPage = restaurantRepository.findAll(pageable);
		Page<RestaurantList> localPage = restaurantEntityMapper.toRestaurantPageList(springPage);
		return localPage;
	}
}
