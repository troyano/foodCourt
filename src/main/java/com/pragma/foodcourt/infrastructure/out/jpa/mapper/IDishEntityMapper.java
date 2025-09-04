package com.pragma.foodcourt.infrastructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pragma.foodcourt.domain.model.Dish;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.DishEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IDishEntityMapper {

    DishEntity toEntity(Dish dish);
    Dish toModel(DishEntity entity);

    Dish toDish(DishEntity dishEntity);
}