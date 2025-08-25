package com.pragma.foodcourt.application.handler;

import com.pragma.foodcourt.application.dto.request.DishRequestDto;

public interface IDishHandler {
    void createDish(DishRequestDto dishRequestDto);
}
