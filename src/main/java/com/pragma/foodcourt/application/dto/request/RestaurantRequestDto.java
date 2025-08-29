package com.pragma.foodcourt.application.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
public class RestaurantRequestDto {

	@NotBlank
	@Size(max = Constants.NUMBER_100, message = Constants.MSG_RESTAURANT_NAME_MAX_LENGTH)
	@Pattern(regexp = Constants.REGEX_RESTAURANT_NAME, message = Constants.MSG_RESTAURANT_NAME_PATTERN)
	private String name;
	@NotBlank
	@Size(max = Constants.NUMBER_50, message = Constants.MSG_RESTAURANT_TAX_ID_MAX_LENGTH)
	@Pattern(regexp = Constants.REGEX_TAX_ID, message = Constants.MSG_INVALID_TAX_ID)
	private String taxId;
	@NotBlank
	@Size(max = Constants.NUMBER_200, message = Constants.MSG_RESTAURANT_ADDRESS_MAX_LENGTH)
	private String address;
	@NotBlank
	@Size(max = Constants.NUMBER_13, message = Constants.MSG_CELL_PHONE_MAX_LENGTH)
	@Pattern(regexp = Constants.REGEX_PHONE, message = Constants.MSG_CELL_PHONE_REGEX)
	private String phone;
	@NotBlank
	@Size(max = Constants.NUMBER_255, message = Constants.MSG_LOGO_URL_MAX_LENGTH)
	private String logoUrl;
	@NotBlank
	@Size(max = Constants.NUMBER_255, message = Constants.MSG_CREATED_BY_MAX_LENGTH_RESTAURANT)
	private String createdBy;
}