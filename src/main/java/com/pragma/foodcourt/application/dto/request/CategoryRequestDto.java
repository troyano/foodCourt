package com.pragma.foodcourt.application.dto.request;

import javax.validation.constraints.NotBlank;
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
public class CategoryRequestDto {
	@NotBlank(message = Constants.MSG_CATEGORY_CODE_REQUIRED)
	@Size(max = Constants.NUMBER_5, message = Constants.MSG_CATEGORY_CODE_MAX_LENGTH)
	private String code;
	@Size(max = Constants.NUMBER_255, message = Constants.MSG_CATEGORY_DESCRIPTION_MAX_LENGTH)
	private String description;
	@NotBlank(message = Constants.MSG_CREATED_BY_REQUIRED)
	@Size(max = Constants.NUMBER_150, message = Constants.MSG_CREATED_BY_MAX_LENGTH)
	private String createdBy;
}
