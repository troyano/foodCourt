package com.pragma.foodcourt.application.handler;

import com.pragma.foodcourt.application.dto.request.RestaurantRequestDto;
import com.pragma.foodcourt.application.dto.response.RestaurantListResponseDto;
import com.pragma.foodcourt.application.dto.response.RestaurantResponseDto;
import org.springframework.data.domain.Page;

public interface IRestaurantHandler {
	RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurantRequestDto);

    Page<RestaurantListResponseDto> listRestaurants(int page, int size);
}