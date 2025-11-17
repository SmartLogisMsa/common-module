package com.smartlogis.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.smartlogis.common.infrastructure.security.HeaderAuthenticationFilter;
import com.smartlogis.common.infrastructure.security.RestAccessDeniedHandler;
import com.smartlogis.common.infrastructure.security.RestAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@Order(1)
public class CommonSecurityConfig {

	private final HeaderAuthenticationFilter headerAuthenticationFilter;
	private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	private final RestAccessDeniedHandler restAccessDeniedHandler;

	@Bean
	@ConditionalOnMissingBean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,
		RestAccessDeniedHandler restAccessDeniedHandler) throws Exception {
		return http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
			.addFilterBefore(headerAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.exceptionHandling(c -> {
				c.authenticationEntryPoint(restAuthenticationEntryPoint);
				c.accessDeniedHandler(restAccessDeniedHandler);
			})
			.build();
	}

}
