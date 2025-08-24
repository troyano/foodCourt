package com.pragma.foodcourt.domain.clients;

public interface IUserRoleValidator {
	boolean isOwner(String userName);
}