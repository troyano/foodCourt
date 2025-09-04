package com.pragma.foodcourt.application.dto.response;

import com.pragma.foodcourt.domain.api.Page;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantListResponseDto {
    private Page<RestaurantListResponseDto> restaurants;
}
