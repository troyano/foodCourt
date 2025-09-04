package com.pragma.foodcourt.application.handler;

import com.pragma.foodcourt.application.dto.request.DishEnableDisableRequestDto;
import com.pragma.foodcourt.application.dto.request.DishRequestDto;
import com.pragma.foodcourt.application.dto.request.DishUpdateRequestDto;

public interface IDishHandler {
    void createDish(DishRequestDto dishRequestDto);

	void updateDishPriceAndDescription(DishUpdateRequestDto requestDto);

    void enableOrDisableDish(DishEnableDisableRequestDto requestDto);
}
