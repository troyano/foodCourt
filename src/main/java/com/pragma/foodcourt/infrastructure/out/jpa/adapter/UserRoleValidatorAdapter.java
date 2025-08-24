package com.pragma.foodcourt.infrastructure.out.jpa.adapter;

import com.pragma.foodcourt.domain.clients.IUserRoleValidator;

public class UserRoleValidatorAdapter implements IUserRoleValidator {
    @Override
    public boolean isOwner(Long userId) {
        // TODO: Implement real logic, for now always returns true
        return true;
    }
}
