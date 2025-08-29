package com.pragma.foodcourt.domain.exception;

import com.pragma.foodcourt.domain.util.Constants;

import java.math.BigDecimal;

public class ValidationUtils {

	public static void validatePhone(String phone) {
		if (!phone.matches(Constants.REGEX_CELL_PHONE)) {
			throw new ValidationException(Constants.MSG_CELL_PHONE_REGEX);
		}
	}

	public static void validateTaxId(String taxId) {
		if (!taxId.matches(Constants.REGEX_TAX_ID)) {
			throw new ValidationException(Constants.MSG_INVALID_TAX_ID);
		}
	}

	public static void validateUserRoleOrThrow(boolean isRole, String errorMessage) {
		if (!isRole) {
			throw new ValidationException(errorMessage);
		}
	}

	public static void validateDishPrice(BigDecimal price) {
		if (price == null) {
			throw new ValidationException(Constants.MSG_PRICE_REQUIRED);
		}
		if (price.compareTo(BigDecimal.ONE) < 0) {
			throw new ValidationException(Constants.MSG_PRICE_MIN);
		}
		if (price.signum() <= 0) {
			throw new ValidationException(Constants.PRICE_POSITIVE);
		}
		if (price.scale() > 0 || price.precision() > 12) {
			throw new ValidationException(Constants.PRICE_DIGITS);
		}
	}

    public static void validateRequiredString(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(message);
        }
    }

	private ValidationUtils() {
		super();
	}
}