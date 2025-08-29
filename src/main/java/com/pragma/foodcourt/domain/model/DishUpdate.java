package com.pragma.foodcourt.domain.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DishUpdate {
	private Long id;
	private BigDecimal price;
	private String description;
	private String createdBy;
}
