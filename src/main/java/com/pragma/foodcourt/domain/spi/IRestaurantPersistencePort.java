package com.pragma.foodcourt.domain.spi;

import com.pragma.foodcourt.domain.api.Page;
import com.pragma.foodcourt.domain.model.Restaurant;
import com.pragma.foodcourt.domain.model.RestaurantList;

import java.util.List;
import java.util.Optional;

public interface IRestaurantPersistencePort {
    Restaurant createRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();

    Optional<String> findOwnerUserById(Long id);

    Page<RestaurantList> listAllRestaurants(int page, int size);
}
