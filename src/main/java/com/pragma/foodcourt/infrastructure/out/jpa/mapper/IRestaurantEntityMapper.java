package com.pragma.foodcourt.infrastructure.out.jpa.mapper;

import com.pragma.foodcourt.domain.api.Page;
import com.pragma.foodcourt.domain.model.RestaurantList;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.pragma.foodcourt.domain.model.Restaurant;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEntityMapper {
    Restaurant toRestaurant(RestaurantEntity restaurantEntity);

    RestaurantEntity toEntity(Restaurant restaurant);

    java.util.List<Restaurant> toRestaurantList(java.util.List<RestaurantEntity> entities);

    RestaurantList toRestaurantList(RestaurantEntity restaurantEntity);

    default Page<RestaurantList> toRestaurantPageList(org.springframework.data.domain.Page<RestaurantEntity> springPage) {
        List<RestaurantList> content = springPage.getContent().stream()
                .map(this::toRestaurantList)
                .collect(Collectors.toList());
        return new Page<>() {

            @Override
            public List<RestaurantList> getContent() {
                return content;
            }

            @Override
            public int getPageNumber() {
                return springPage.getNumber();
            }

            @Override
            public int getPageSize() {
                return springPage.getSize();
            }

            @Override
            public long getTotalElements() {
                return springPage.getTotalElements();
            }

            @Override
            public int getTotalPages() {
                return springPage.getTotalPages();
            }

            @Override
            public boolean isLast() {
                return springPage.isLast();
            }
        };
    }
}
