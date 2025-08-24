package com.pragma.foodcourt.application.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequestDto {

	@NotBlank
	@Size(max = 100, message = "The name cannot exceed 100 characters")
	@Pattern(regexp = "^(?=.*[a-zA-Z])(?!\\d+$).+$", message = "The name must contain at least one letter and cannot be only numbers")
	private String name;
	@NotBlank
	@Size(max = 50, message = "The taxId cannot exceed 50 characters")
	@Pattern(regexp = "\\d+", message = "The document must be numeric")
	private String taxId;
	@NotBlank
	@Size(max = 200, message = "The address cannot exceed 200 characters")
	private String address;
	@NotBlank
	@Size(max = 13, message = "The cell phone cannot exceed 13 characters")
	@Pattern(regexp = "^\\+?[0-9]*$", message = "The cell phone can only contain numbers and optionally the + symbol")
	private String phone;
	@NotBlank
	@Size(max = 255, message = "The logoUrl cannot exceed 255 characters")
	private String logoUrl;
	@NotBlank
	@Size(max = 255, message = "The createdBy cannot exceed 255 characters")
	private String createdBy;
}
