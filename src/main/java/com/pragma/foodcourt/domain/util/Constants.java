package com.pragma.foodcourt.domain.util;

public class Constants {
	public static final String ROLE_ADM = "ADM";
	public static final String ROLE_OWNER = "OWN";

	public static final String ROLE_CLIENT = "CLI";
	// Messages

	public static final String MSG_IDENTIFIER_IS_REQUIRED = "identifier is required";
	public static final String MSG_INVALID_TAX_ID = "The document must be numeric";
	public static final String MSG_INVALID_CELL_PHONE = "Invalid cell phone";
	public static final String MSG_CELL_PHONE_MAX_LENGTH = "The cell phone cannot exceed 13 characters";
	public static final String MSG_CELL_PHONE_REGEX = "The cell phone can only contain numbers and optionally the + symbol";
	public static final String MSG_ID_NUMERIC = "The document must be numeric";
	public static final String MSG_CATEGORY_CODE_MAX_LENGTH = "The code cannot exceed 5 characters";
	public static final String MSG_CATEGORY_DESCRIPTION_MAX_LENGTH = "The description cannot exceed 255 characters";
	public static final String MSG_CREATED_BY_MAX_LENGTH = "The createdBy cannot exceed 150 characters";
	public static final String MSG_NAME_REQUIRED = "The name is required";
	public static final String MSG_NAME_MAX_LENGTH = "The name cannot exceed 200 characters";
	public static final String MSG_PRICE_REQUIRED = "The price is required";
	public static final String MSG_PRICE_MIN = "The price must be a positive integer greater than 0";
    public static final String PRICE_POSITIVE = "Price must be greater than 0";
    public static final String PRICE_DIGITS = "Price must be an integer without decimals, up to 12 digits";
	public static final String MSG_DESCRIPTION_REQUIRED = "The description is required";
	public static final String MSG_DESCRIPTION_MAX_LENGTH = "The description cannot exceed 255 characters";
	public static final String MSG_IMAGE_URL_REQUIRED = "The imageUrl is required";
	public static final String MSG_IMAGE_URL_MAX_LENGTH = "The imageUrl cannot exceed 255 characters";
	public static final String MSG_RESTAURANT_TAX_ID_REQUIRED = "The restaurantTaxId is required";
	public static final String MSG_RESTAURANT_TAX_ID_MAX_LENGTH = "The restaurantTaxId cannot exceed 50 characters";
	public static final String MSG_CATEGORY_CODE_REQUIRED = "The categoryCode is required";
	public static final String MSG_CATEGORY_CODE_MAX_LENGTH_DISH = "The categoryCode cannot exceed 5 characters";
	public static final String MSG_CREATED_BY_REQUIRED = "The createdBy is required";
	public static final String MSG_CREATED_BY_MAX_LENGTH_DISH = "The createdBy cannot exceed 150 characters";
	public static final String MSG_RESTAURANT_NAME_MAX_LENGTH = "The name cannot exceed 100 characters";
	public static final String MSG_RESTAURANT_NAME_PATTERN = "The name must contain at least one letter and cannot be only numbers";
	public static final String MSG_RESTAURANT_ADDRESS_MAX_LENGTH = "The address cannot exceed 200 characters";
	public static final String MSG_LOGO_URL_MAX_LENGTH = "The logoUrl cannot exceed 255 characters";
	public static final String MSG_CREATED_BY_MAX_LENGTH_RESTAURANT = "The createdBy cannot exceed 255 characters";
	public static final String MSG_CATEGORY_NOT_FOUND = "Category not found";
	public static final String MSG_USER_NOT_AUTHORIZED_AS_OWNER = "The user is not authorized as an owner";
	public static final String MSG_USER_NOT_AUTHORIZED_AS_ADM = "The user is not authorized as an administrator";
	public static final String MSG_RESTAURANT_NOT_FOUNT = "Restaurant not found";
	public static final String MSG_DISH_NOT_FOUND = "Dish not found";
	public static final String RESTAURANT_NOT_FOUND_MESSAGE = "Restaurant not found";
	public static final String NOT_RESTAURANT_OWNER_MESSAGE = "is not the owner of the restaurant";

	// Regular Expressions
	public static final String REGEX_TAX_ID = "\\d+";
	public static final String REGEX_CELL_PHONE = "^[+]?[0-9]{1,13}$";
	public static final String REGEX_RESTAURANT_NAME = "^(?=.*[a-zA-Z])(?!\\d+$).+$";
	public static final String REGEX_PHONE = "^\\+?\\d*$";
	// Magic Numbers
	public static final int NUMBER_5 = 5;
	public static final int NUMBER_13 = 13;
	public static final int NUMBER_18 = 18;
	public static final int NUMBER_15 = 15;
	public static final int NUMBER_50 = 50;
	public static final int NUMBER_100 = 100;
	public static final int NUMBER_150 = 150;
	public static final int NUMBER_200 = 200;
	public static final int NUMBER_255 = 255;
	public static final int NUMBER_1990 = 0;

	private Constants() {
	}
}