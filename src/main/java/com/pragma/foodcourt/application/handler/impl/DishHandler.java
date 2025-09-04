package com.pragma.foodcourt.application.handler.impl;

import com.pragma.foodcourt.application.dto.request.DishEnableDisableRequestDto;
import com.pragma.foodcourt.domain.api.IUpdateDishServicePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pragma.foodcourt.application.dto.request.DishRequestDto;
import com.pragma.foodcourt.application.dto.request.DishUpdateRequestDto;
import com.pragma.foodcourt.application.handler.IDishHandler;
import com.pragma.foodcourt.application.mapper.IDishRequestMapper;
import com.pragma.foodcourt.domain.api.IDishServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {
	private final IDishServicePort dishServicePort;
	private final IUpdateDishServicePort updateDishServicePort;
	private final IDishRequestMapper dishRequestMapper;

	@Override
	public void createDish(DishRequestDto dishRequestDto) {
		dishServicePort.createDish(dishRequestMapper.toDish(dishRequestDto));
	}

	@Override
	public void updateDishPriceAndDescription(DishUpdateRequestDto requestDto) {
		updateDishServicePort.updateDishPriceAndDescription(dishRequestMapper.toDish(requestDto));
	}

	@Override
	public void enableOrDisableDish(DishEnableDisableRequestDto requestDto) {
		updateDishServicePort.enableOrDisableDish(requestDto.getId(), requestDto.isActive());
	}
}
