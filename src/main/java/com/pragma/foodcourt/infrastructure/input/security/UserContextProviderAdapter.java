package com.pragma.foodcourt.infrastructure.input.security;

import com.pragma.foodcourt.domain.spi.IUserContextProviderPort;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserContextProviderAdapter implements IUserContextProviderPort {

    @Override
    public String getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof String) {
            return (String) principal;
        }
        return null;
    }
}
