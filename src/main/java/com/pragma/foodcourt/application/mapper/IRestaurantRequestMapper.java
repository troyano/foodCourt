package com.pragma.foodcourt.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.pragma.foodcourt.application.dto.request.RestaurantRequestDto;
import com.pragma.foodcourt.domain.model.Restaurant;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantRequestMapper {
    Restaurant toRestaurant(RestaurantRequestDto restaurantRequestDto);
}
