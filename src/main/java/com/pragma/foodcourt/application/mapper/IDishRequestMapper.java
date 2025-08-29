package com.pragma.foodcourt.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pragma.foodcourt.application.dto.request.DishRequestDto;
import com.pragma.foodcourt.application.dto.request.DishUpdateRequestDto;
import com.pragma.foodcourt.domain.model.Dish;
import com.pragma.foodcourt.domain.model.DishUpdate;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper {

	Dish toDish(DishRequestDto dishRequestDto);

	DishUpdate toDish(DishUpdateRequestDto requestDto);
}