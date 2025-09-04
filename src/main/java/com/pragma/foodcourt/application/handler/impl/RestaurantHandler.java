package com.pragma.foodcourt.application.handler.impl;

import com.pragma.foodcourt.application.dto.response.RestaurantListResponseDto;
import com.pragma.foodcourt.domain.api.Page;
import com.pragma.foodcourt.domain.model.RestaurantList;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
	public org.springframework.data.domain.Page<RestaurantListResponseDto> listRestaurants(int page, int size) {
		Page<RestaurantList> restaurantPage = restaurantServicePort.listRestaurants(page, size);
		return new PageImpl<>(
				restaurantResponseMapper.toResponseList(restaurantPage.getContent()),
				PageRequest.of(restaurantPage.getPageNumber(), restaurantPage.getPageSize()),
				restaurantPage.getTotalElements());
	}
}