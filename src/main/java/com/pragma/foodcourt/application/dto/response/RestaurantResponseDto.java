package com.pragma.foodcourt.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private Long taxId;
    private String address;
    private String phone;
    private String logoUrl;
    private Long createdBy;
}