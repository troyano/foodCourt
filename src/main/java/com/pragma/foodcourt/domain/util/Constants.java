package com.pragma.foodcourt.domain.util;

public class Constants {

	// Messages
	public static final String MSG_INVALID_TAX_ID = "The document must be numeric";
	public static final String MSG_INVALID_CELL_PHONE = "Invalid cell phone";
	public static final String MSG_USER_NOT_AUTORIZED_AS_OWNER = "The user is not authorized as an owner";
	// Regular Expressions
	public static final String REGEX_TAX_ID = "\\d+";
	public static final String REGEX_CELL_PHONE = "^[+]?[0-9]{1,13}$";
	// Magic Numbers
	public static final int NUMERO_18 = 18;

	private Constants() {
	}
}
