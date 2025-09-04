package com.pragma.foodcourt.infrastructure.input.rest;

import javax.validation.Valid;

import com.pragma.foodcourt.application.dto.request.DishEnableDisableRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.foodcourt.application.dto.request.DishRequestDto;
import com.pragma.foodcourt.application.dto.request.DishUpdateRequestDto;
import com.pragma.foodcourt.application.dto.response.DishResponseDto;
import com.pragma.foodcourt.application.handler.IDishHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import static com.pragma.foodcourt.domain.util.Constants.ROLE_OWNER;

@Tag(name = "Dish", description = "Dish management API")
@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
public class DishRestController {
    private final IDishHandler dishHandler;

    @Operation(summary = "Create a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
            @ApiResponse(responseCode = "403", description = "User not authorized as owner", content = @Content),
            @ApiResponse(responseCode = "404", description = "Restaurant or category not found", content = @Content)
    })
    @PostMapping
    @PreAuthorize("hasRole('" + ROLE_OWNER + "')")
    public ResponseEntity<DishResponseDto> createDish(@RequestBody @Valid DishRequestDto requestDto) {
        dishHandler.createDish(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update price and description of a dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
            @ApiResponse(responseCode = "403", description = "User not authorized as owner", content = @Content),
            @ApiResponse(responseCode = "404", description = "Dish not found", content = @Content)
    })
    @PatchMapping
    @PreAuthorize("hasRole('" + ROLE_OWNER + "')")
    public ResponseEntity<Void> updateDishPriceAndDescription(@RequestBody @Valid DishUpdateRequestDto requestDto) {
        dishHandler.updateDishPriceAndDescription(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Enabled o disabled a dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
            @ApiResponse(responseCode = "403", description = "User not authorized as owner", content = @Content),
            @ApiResponse(responseCode = "404", description = "Dish not found", content = @Content)
    })
    @PatchMapping("/enable-disable")
    @PreAuthorize("hasRole('" + ROLE_OWNER + "')")
    public ResponseEntity<Void> enableOrDisableDish(@RequestBody @Valid DishEnableDisableRequestDto requestDto) {
        dishHandler.enableOrDisableDish(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
