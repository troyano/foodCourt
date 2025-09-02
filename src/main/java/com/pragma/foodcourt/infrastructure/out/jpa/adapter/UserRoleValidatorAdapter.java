package com.pragma.foodcourt.infrastructure.out.jpa.adapter;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.pragma.foodcourt.domain.clients.IUserRoleValidator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserRoleValidatorAdapter implements IUserRoleValidator {
    private final RestTemplate restTemplate;
    private final String userServiceUrl;

    @Override
    public boolean isRole(String userName, String role) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(userServiceUrl)
                    .pathSegment("users")
                .pathSegment(userName, "is-role", role)
                .toUriString();
            System.out.println( "-----> URL: " + url);
            Boolean response = restTemplate.getForObject(url, Boolean.class);
            return Boolean.TRUE.equals(response);
        } catch (Exception e) {
            // Log error and return false if user service is unavailable or returns error
            return false;
        }
    }
}