package com.pragma.foodcourt.domain.exception;

import com.pragma.foodcourt.domain.util.Constants;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException() {
        super(
                Constants.NOT_RESTAURANT_OWNER_MESSAGE
        );
    }
}
