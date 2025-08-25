package com.pragma.foodcourt.application.handler.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pragma.foodcourt.application.dto.request.DishRequestDto;
import com.pragma.foodcourt.application.handler.IDishHandler;
import com.pragma.foodcourt.application.mapper.IDishRequestMapper;
import com.pragma.foodcourt.domain.api.IDishServicePort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {
	private final IDishServicePort dishServicePort;
	private final IDishRequestMapper dishRequestMapper;

	@Override
	public void createDish(DishRequestDto dishRequestDto) {
		dishServicePort.createDish(dishRequestMapper.toDish(dishRequestDto));
	}
}
