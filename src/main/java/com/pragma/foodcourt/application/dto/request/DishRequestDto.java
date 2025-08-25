package com.pragma.foodcourt.application.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishRequestDto {
	@NotBlank(message = "The name is required")
	@Size(max = 200, message = "The name cannot exceed 200 characters")
	private String name;

	@NotNull(message = "The price is required")
	@Min(value = 1, message = "The price must be a positive integer greater than 0")
	private BigDecimal price;

	@NotBlank(message = "The description is required")
	@Size(max = 255, message = "The description cannot exceed 255 characters")
	private String description;

	@NotBlank(message = "The imageUrl is required")
	@Size(max = 255, message = "The imageUrl cannot exceed 255 characters")
	private String imageUrl;

	@NotBlank(message = "The restaurantTaxId is required")
	@Size(max = 50, message = "The restaurantTaxId cannot exceed 50 characters")
	private String restaurantTaxId;

	@NotBlank(message = "The categoryCode is required")
	@Size(max = 5, message = "The categoryCode cannot exceed 5 characters")
	private String categoryCode;

	@NotBlank
	@Size(max = 150, message = "The createdBy cannot exceed 150 characters")
	private String createdBy;
}
