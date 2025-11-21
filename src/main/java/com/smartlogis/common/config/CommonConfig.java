package com.smartlogis.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smartlogis.common.exception.GlobalExceptionHandler;
import com.smartlogis.common.infrastructure.MessageResolver;
import com.smartlogis.common.infrastructure.security.HeaderAuthenticationFilter;
import com.smartlogis.common.infrastructure.security.RestAccessDeniedHandler;
import com.smartlogis.common.infrastructure.security.RestAuthenticationEntryPoint;
import com.smartlogis.common.utils.QuerydslSortUtils;

@Configuration
public class CommonConfig {

	@Bean
	@ConditionalOnMissingBean
	public MessageResolver messageResolver(MessageSource messageSource) {
		return new MessageResolver(messageSource);
	}

	@Bean
	@ConditionalOnMissingBean
	public GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

	@Bean
	@ConditionalOnMissingBean
	public HeaderAuthenticationFilter headerAuthenticationFilter() {
		return new HeaderAuthenticationFilter();
	}

	@Bean
	@ConditionalOnMissingBean
	public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	@ConditionalOnMissingBean
	public RestAccessDeniedHandler restAccessDeniedHandler() {
		return new RestAccessDeniedHandler();
	}

	@Bean
	@ConditionalOnMissingBean
	public QuerydslSortUtils querydslSortUtils() {
		return new QuerydslSortUtils();
	}
}
