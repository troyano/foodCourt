package com.pragma.foodcourt.infrastructure.configuration;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.nimbusds.jose.jwk.RSAKey;

@Component
public class JwtValidator {

    private final String userServiceUrl;
    private final RSAKey rsaKey;

    public JwtValidator(@Value("${users.service-url}") String userServiceUrl, JwkSetFetcher jwkSetFetcher) {
        this.userServiceUrl = userServiceUrl;
        this.rsaKey = jwkSetFetcher.fetchPublicKey(userServiceUrl.concat("/auth/jwks.json"));
    }

    public boolean validateToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            RSASSAVerifier verifier = new RSASSAVerifier(rsaKey.toRSAPublicKey());
            return signedJWT.verify(verifier);
        } catch (Exception e) {
            System.out.println("------------------------------>Error validating token: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public String extractClaim(String token, String claim) throws java.text.ParseException {
        JWSObject jwsObject = JWSObject.parse(token);
        Object claimValue = jwsObject.getPayload().toJSONObject().get(claim);
        return claimValue != null ? claimValue.toString() : null;
    }
}