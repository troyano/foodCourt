package com.pragma.foodcourt.domain.api;

import com.pragma.foodcourt.domain.model.Restaurant;
import java.util.List;

public interface IRestaurantServicePort {
    List<Restaurant> getAllRestaurants();
    Restaurant createRestaurant(Restaurant restaurant);
}