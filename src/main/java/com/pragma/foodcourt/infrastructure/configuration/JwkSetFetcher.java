package com.pragma.foodcourt.infrastructure.configuration;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;


@Service
public class JwkSetFetcher {

    private final RestTemplate restTemplate;

    public JwkSetFetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RSAKey fetchPublicKey(String jwksUrl) {
        try {
            JWKSet jwkSet = JWKSet.load(new URL(jwksUrl));
            return (RSAKey) jwkSet.getKeys().get(0);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching JWKSet", e);
        }
    }
}