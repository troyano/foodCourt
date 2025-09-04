package com.pragma.foodcourt.infrastructure.configuration;

import com.nimbusds.jose.JWSObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.nimbusds.jose.jwk.RSAKey;

@Component
public class JwtValidator {

    private String userServiceUrl;
    private RSAKey rsaKey;

    public JwtValidator(@Value("${users.service-url}") String userServiceUrl, JwkSetFetcher jwkSetFetcher) {
        this.userServiceUrl = userServiceUrl;
        this.rsaKey = jwkSetFetcher.fetchPublicKey(userServiceUrl.concat("/auth/jwks.json"));
    }

    // Constructor to testing purposes
    public JwtValidator(RSAKey rsaKey) {
        this.userServiceUrl = null;
        this.rsaKey = rsaKey;
    }
    public JwtValidator() {
        super();
    }

    public String extractClaim(String token, String claim) throws java.text.ParseException {
        JWSObject jwsObject = JWSObject.parse(token);
        Object claimValue = jwsObject.getPayload().toJSONObject().get(claim);
        return claimValue != null ? claimValue.toString() : null;
    }
}