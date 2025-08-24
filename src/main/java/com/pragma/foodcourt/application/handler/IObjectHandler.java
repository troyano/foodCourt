package com.pragma.foodcourt.application.handler;

import java.util.List;

import com.pragma.foodcourt.application.dto.request.ObjectRequestDto;
import com.pragma.foodcourt.application.dto.response.ObjectResponseDto;

public interface IObjectHandler {

    void saveObject(ObjectRequestDto objectRequestDto);

    List<ObjectResponseDto> getAllObjects();
}