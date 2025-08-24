package com.pragma.foodcourt.domain.spi;

import com.pragma.foodcourt.domain.model.Restaurant;
import java.util.List;

public interface IRestaurantPersistencePort {
    Restaurant createRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();
}