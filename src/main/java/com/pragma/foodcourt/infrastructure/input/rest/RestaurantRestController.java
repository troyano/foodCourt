package com.pragma.foodcourt.infrastructure.input.rest;

import com.pragma.foodcourt.application.dto.response.RestaurantListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.pragma.foodcourt.application.dto.request.RestaurantRequestDto;
import com.pragma.foodcourt.application.dto.response.RestaurantResponseDto;
import com.pragma.foodcourt.application.handler.IRestaurantHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import static com.pragma.foodcourt.domain.util.Constants.ROLE_ADM;
import static com.pragma.foodcourt.domain.util.Constants.ROLE_CLIENT;

@Tag(name = "Restaurant", description = "Restaurant management API")
@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantRestController {
	private final IRestaurantHandler restaurantHandler;

	@Operation(summary = "Create a new restaurant")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Restaurant created", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid data", content = @Content),
			@ApiResponse(responseCode = "409", description = "Restaurant already exists", content = @Content),
			@ApiResponse(responseCode = "403", description = "User not authorized as owner", content = @Content)})
	@PostMapping
	@PreAuthorize("hasRole('" + ROLE_ADM + "')")
	public ResponseEntity<RestaurantResponseDto> createRestaurant(@RequestBody @Valid RestaurantRequestDto requestDto) {
		RestaurantResponseDto responseDto = restaurantHandler.createRestaurant(requestDto);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	@Operation(summary = "Listar restaurantes paginados y ordenados alfabéticamente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Restaurantes listados correctamente", content = @Content),
			@ApiResponse(responseCode = "401", description = "No autorizado para ver el recurso", content = @Content)
	})
	@GetMapping("/")
	@PreAuthorize("hasRole('" + ROLE_CLIENT + "')")
	public ResponseEntity<Page<RestaurantListResponseDto>> listRestaurants(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size
	) {
		return ResponseEntity.ok(restaurantHandler.listRestaurants(page, size));
	}
}