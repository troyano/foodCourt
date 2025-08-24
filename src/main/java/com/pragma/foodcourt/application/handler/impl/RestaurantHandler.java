package com.pragma.foodcourt.application.handler.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pragma.foodcourt.application.dto.request.RestaurantRequestDto;
import com.pragma.foodcourt.application.dto.response.RestaurantResponseDto;
import com.pragma.foodcourt.application.handler.IRestaurantHandler;
import com.pragma.foodcourt.application.mapper.IRestaurantRequestMapper;
import com.pragma.foodcourt.application.mapper.IRestaurantResponseMapper;
import com.pragma.foodcourt.domain.api.IRestaurantServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {
	private final IRestaurantServicePort restaurantServicePort;
	private final IRestaurantRequestMapper restaurantRequestMapper;
	private final IRestaurantResponseMapper restaurantResponseMapper;

	@Override
	public RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto) {
		return restaurantResponseMapper.toResponse(
				restaurantServicePort.createRestaurant(restaurantRequestMapper.toRestaurant(restaurantRequestDto)));
	}

	@Override
	public List<RestaurantResponseDto> getAllRestaurants() {
		return restaurantResponseMapper.toResponseList(restaurantServicePort.getAllRestaurants());
	}
}