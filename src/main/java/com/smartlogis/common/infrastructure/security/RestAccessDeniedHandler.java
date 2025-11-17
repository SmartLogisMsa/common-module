package com.smartlogis.common.infrastructure.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartlogis.common.presentation.ApiResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RestAccessDeniedHandler implements AccessDeniedHandler {

	@Value("${spring.application.name:UNKNOWN-SERVICE")
	private String service;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);

		ApiResponse<?> apiResponse = ApiResponse.failure(service.toUpperCase() + ".FORBIDDEN");
		response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
	}
}
