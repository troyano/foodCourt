package com.pragma.foodcourt.application.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class DishUpdateRequestDto {

	@NotNull(message = Constants.MSG_IDENTIFIER_IS_REQUIRED)
	private Long id;

	@NotNull(message = Constants.MSG_PRICE_REQUIRED)
	@Min(value = 1, message = Constants.MSG_PRICE_MIN)
	private BigDecimal price;

	@NotBlank(message = Constants.MSG_DESCRIPTION_REQUIRED)
	@Size(max = Constants.NUMBER_255, message = Constants.MSG_DESCRIPTION_MAX_LENGTH)
	private String description;

	@NotBlank(message = Constants.MSG_CREATED_BY_REQUIRED)
	@Size(max = Constants.NUMBER_150, message = Constants.MSG_CREATED_BY_MAX_LENGTH_DISH)
	private String createdBy;
}
