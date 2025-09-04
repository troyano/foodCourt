package com.pragma.foodcourt.infrastructure.configuration;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtValidator jwtValidator;
    private static final String MSG_NOT_AUTHORIZED = "Authorization header is missing or invalid";

    public JwtAuthorizationFilter(JwtValidator jwtValidator) {
        this.jwtValidator = jwtValidator;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
            response.getWriter().write(MSG_NOT_AUTHORIZED);
            return;
        } else {
            String token = authorizationHeader.substring(7);
            try {
                String email = jwtValidator.extractClaim(token, "email");
                String role = jwtValidator.extractClaim(token, "roles");

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, null,
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (ParseException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                response.getWriter().write(MSG_NOT_AUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
