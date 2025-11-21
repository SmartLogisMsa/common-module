package com.smartlogis.common.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableFeignClients("com.smartlogis")
@RequiredArgsConstructor
public class FeignConfig {

	private static final String HEADER_USER_ID = "X-User-Id";
	private static final String HEADER_USER_NAME = "X-User-Name";
	private static final String HEADER_ROLES = "X-User-Role";

	/**
	 * OpenFeign 요청시 JWT 토큰이 있다면 함께 전송
	 * @return
	 */
	@Bean
	public RequestInterceptor requestInterceptor() {

		return tpl -> {
			RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
			if (attrs instanceof ServletRequestAttributes) {
				HttpServletRequest req = ((ServletRequestAttributes)attrs).getRequest();
				String authorization = req.getHeader("Authorization");
				if (StringUtils.hasText(authorization)) { // user-service 인증 용
					tpl.header("Authorization", authorization);
				}

				// 그외 나머지 서비스들은 하기 헤더 정보로 인증
				String userId = req.getHeader(HEADER_USER_ID);
				String username = req.getHeader(HEADER_USER_NAME);
				String roles = req.getHeader(HEADER_ROLES);

				if (StringUtils.hasText(userId))
					tpl.header(HEADER_USER_ID, userId);
				if (StringUtils.hasText(username))
					tpl.header(HEADER_USER_NAME, username);
				if (StringUtils.hasText(roles))
					tpl.header(HEADER_ROLES, roles);
			}

		};
	}

}

