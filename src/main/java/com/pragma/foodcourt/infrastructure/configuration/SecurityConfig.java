package com.pragma.foodcourt.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

	private final JwtAuthorizationFilter jwtAuthorizationFilter;
	public SecurityConfig(JwtAuthorizationFilter jwtAuthorizationFilter) {
		this.jwtAuthorizationFilter = jwtAuthorizationFilter;
	}
	private static final String[] PUBLIC_ENDPOINTS = {
			"/swagger-ui/**",
			"/v3/api-docs/**"
	};
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(auth -> auth
						.antMatchers(PUBLIC_ENDPOINTS).permitAll()
						.anyRequest().authenticated()
				)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public UrlBasedCorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		//config.setAllowedOrigins(List.of("https://trusted-domain.com")); // Replace with your trusted domains
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
		config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
