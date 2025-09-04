package com.pragma.foodcourt.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dish {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private Boolean isActive;
    private String restaurantTaxId;
    private String categoryCode;
    private String createdBy;
    private LocalDateTime createdAt;
    private Long restaurantId;
    private Long categoryId;
    private boolean active;
}
