package com.pragma.foodcourt.application.dto.request;

import com.pragma.foodcourt.domain.util.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DishEnableDisableRequestDto {
    @NotNull(message = Constants.MSG_IDENTIFIER_IS_REQUIRED)
    private int id;
    private boolean active;
}
