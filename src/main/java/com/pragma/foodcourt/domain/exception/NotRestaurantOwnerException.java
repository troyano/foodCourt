package com.pragma.foodcourt.domain.exception;

import com.pragma.foodcourt.domain.util.Constants;

public class NotRestaurantOwnerException extends RuntimeException {
    public NotRestaurantOwnerException() {
        super(
                Constants.NOT_RESTAURANT_OWNER_MESSAGE
        );
    }
}
