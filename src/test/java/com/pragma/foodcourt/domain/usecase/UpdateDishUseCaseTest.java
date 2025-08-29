package com.pragma.foodcourt.domain.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import com.pragma.foodcourt.domain.clients.IUserRoleValidator;
import com.pragma.foodcourt.domain.exception.ResourceNotFoundException;
import com.pragma.foodcourt.domain.exception.ValidationException;
import com.pragma.foodcourt.domain.model.DishUpdate;
import com.pragma.foodcourt.domain.spi.IDishPersistencePort;
import com.pragma.foodcourt.domain.util.Constants;
import com.pragma.foodcourt.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.foodcourt.infrastructure.out.jpa.repository.IDishRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UpdateDishUseCaseTest {

    @Mock
    private IDishPersistencePort dishPersistencePort;
    @Mock
    private IUserRoleValidator userRoleValidator;
    @Mock
    private IDishRepository dishRepository;
    @InjectMocks
    private UpdateDishUseCase updateDishUseCase;

    private DishUpdate validDishUpdate;
    private DishEntity existingDish;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validDishUpdate = new DishUpdate();
        validDishUpdate.setId(1L);
        validDishUpdate.setCreatedBy("owner");
        validDishUpdate.setPrice(new BigDecimal("15"));
        validDishUpdate.setDescription("Updated description");

        existingDish = new DishEntity();
        existingDish.setId(1L);
        existingDish.setPrice(new BigDecimal("10"));
        existingDish.setDescription("Old description");
    }

    @Test
    void updateDishPriceAndDescription_validData_success() {
        when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(true);
        when(dishRepository.findById(anyLong())).thenReturn(Optional.of(existingDish));

        updateDishUseCase.updateDishPriceAndDescription(validDishUpdate);

        verify(dishPersistencePort).updateDishPriceAndDescription(existingDish);
        assertEquals(validDishUpdate.getPrice(), existingDish.getPrice());
        assertEquals(validDishUpdate.getDescription(), existingDish.getDescription());
    }

    @Test
    void updateDishPriceAndDescription_userNotOwner_throwsValidationException() {
        when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(false);

        ValidationException exception = assertThrows(ValidationException.class, () -> updateDishUseCase.updateDishPriceAndDescription(validDishUpdate));

        assertEquals(Constants.MSG_USER_NOT_AUTHORIZED_AS_OWNER, exception.getMessage());
    }

    @Test
    void updateDishPriceAndDescription_dishNotFound_throwsResourceNotFoundException() {
        when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(true);
        when(dishRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> updateDishUseCase.updateDishPriceAndDescription(validDishUpdate));

        assertEquals(Constants.MSG_DISH_NOT_FOUND, exception.getMessage());
    }

    @Test
    void updateDishPriceAndDescription_invalidPrice_throwsValidationException() {
        validDishUpdate.setPrice(new BigDecimal("-5"));
        when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(true);
        ValidationException exception = assertThrows(ValidationException.class, () -> updateDishUseCase.updateDishPriceAndDescription(validDishUpdate));

        assertEquals(Constants.MSG_PRICE_MIN, exception.getMessage());
    }

    @Test
    void updateDishPriceAndDescription_invalidDescription_throwsValidationException() {
        validDishUpdate.setDescription("");
        when(userRoleValidator.isRole(anyString(), anyString())).thenReturn(true);
        ValidationException exception = assertThrows(ValidationException.class, () -> updateDishUseCase.updateDishPriceAndDescription(validDishUpdate));

        assertEquals(Constants.MSG_DESCRIPTION_REQUIRED, exception.getMessage());
    }
}
