package com.pragma.foodcourt.domain.clients;

public interface IUserRoleValidator {
	boolean isRole(String userName, String role);
}