package com.pragma.foodcourt.infrastructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.pragma.foodcourt.domain.model.Restaurant;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.RestaurantEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEntityMapper {
    Restaurant toRestaurant(RestaurantEntity restaurantEntity);
    RestaurantEntity toEntity(Restaurant restaurant);
    java.util.List<Restaurant> toRestaurantList(java.util.List<RestaurantEntity> entities);
}