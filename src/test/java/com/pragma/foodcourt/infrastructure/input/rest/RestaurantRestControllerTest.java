package com.pragma.foodcourt.infrastructure.input.rest;

import com.pragma.foodcourt.application.dto.request.RestaurantRequestDto;
import com.pragma.foodcourt.application.dto.response.RestaurantResponseDto;
import com.pragma.foodcourt.application.handler.IRestaurantHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantRestControllerTest {
    @Mock
    private IRestaurantHandler restaurantHandler;

    @InjectMocks
    private RestaurantRestController restaurantRestController;

    private RestaurantRequestDto requestDto;
    private RestaurantResponseDto responseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        requestDto = new RestaurantRequestDto();
        responseDto = new RestaurantResponseDto();
    }

    @Test
    void createRestaurant_returnsCreated() {
        when(restaurantHandler.createRestaurant(requestDto)).thenReturn(responseDto);
        ResponseEntity<RestaurantResponseDto> response = restaurantRestController.createRestaurant(requestDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(restaurantHandler).createRestaurant(requestDto);
    }
}
