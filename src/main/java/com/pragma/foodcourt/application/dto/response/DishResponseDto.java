package com.pragma.foodcourt.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishResponseDto {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Boolean isActive;
    private Long restaurantId;
    private Long categoryId;
    private String createdBy;
}
