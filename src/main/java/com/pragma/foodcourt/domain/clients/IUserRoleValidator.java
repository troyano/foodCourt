package com.pragma.foodcourt.domain.clients;

public interface IUserRoleValidator {
	boolean isOwner(Long userId);
}