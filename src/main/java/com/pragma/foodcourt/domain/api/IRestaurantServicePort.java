package com.pragma.foodcourt.domain.api;

import com.pragma.foodcourt.domain.model.Restaurant;
import com.pragma.foodcourt.domain.model.RestaurantList;

import java.util.List;

public interface IRestaurantServicePort {
    List<Restaurant> getAllRestaurants();
    Restaurant createRestaurant(Restaurant restaurant);

    Page<RestaurantList> listRestaurants(int page, int size);
}