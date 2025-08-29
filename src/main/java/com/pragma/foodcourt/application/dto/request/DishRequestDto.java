package com.pragma.foodcourt.application.dto.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.pragma.foodcourt.domain.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishRequestDto {
	@NotBlank(message = Constants.MSG_NAME_REQUIRED)
	@Size(max = Constants.NUMBER_200, message = Constants.MSG_NAME_MAX_LENGTH)
	private String name;

	@NotNull(message = Constants.MSG_PRICE_REQUIRED)
	@Min(value = 1, message = Constants.MSG_PRICE_MIN)
	@Positive(message = Constants.PRICE_POSITIVE)
	@Digits(integer = 12, fraction = 0, message = Constants.PRICE_DIGITS)
	private java.math.BigDecimal price;

	@NotBlank(message = Constants.MSG_DESCRIPTION_REQUIRED)
	@Size(max = Constants.NUMBER_255, message = Constants.MSG_DESCRIPTION_MAX_LENGTH)
	private String description;

	@NotBlank(message = Constants.MSG_IMAGE_URL_REQUIRED)
	@Size(max = Constants.NUMBER_255, message = Constants.MSG_IMAGE_URL_MAX_LENGTH)
	private String imageUrl;

	@NotBlank(message = Constants.MSG_RESTAURANT_TAX_ID_REQUIRED)
	@Size(max = Constants.NUMBER_50, message = Constants.MSG_RESTAURANT_TAX_ID_MAX_LENGTH)
	private String restaurantTaxId;

	@NotBlank(message = Constants.MSG_CATEGORY_CODE_REQUIRED)
	@Size(max = Constants.NUMBER_5, message = Constants.MSG_CATEGORY_CODE_MAX_LENGTH_DISH)
	private String categoryCode;

	@NotBlank(message = Constants.MSG_CREATED_BY_REQUIRED)
	@Size(max = Constants.NUMBER_150, message = Constants.MSG_CREATED_BY_MAX_LENGTH_DISH)
	private String createdBy;
}
