package com.smartlogis.common.infrastructure.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartlogis.common.presentation.ApiResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Value("${spring.application.name:UNKNOWN-SERVICE")
	private String service;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		ApiResponse<?> apiResponse = ApiResponse.failure(service.toUpperCase() + ".UNAUTHORIZED");
		response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
	}
}
