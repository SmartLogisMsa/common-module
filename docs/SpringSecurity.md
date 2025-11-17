# Spring Security
Common Module은 MSA 환경에서 Gateway가 인증을 담당하고 각 서비스에는 사용자 정보만 전달되는 구조를 지원합니다.<br>
이를 위해 HTTP 헤더 기반으로 인증을 구성하는 AuthenticatedUser 와 HeaderAuthenticationFilter 를 제공합니다.

## 사용 방법
```java
@PreAuthorize("hasRole('MASTER')")
@GetMapping()
public ResponseEntity<ApiResponse<UserResponse>> getUser(
        @AuthenticationPrincipal AuthenticatedUser user
) {
    user.getId();
    user.getUsername();
    user.getAuthorities();
}
```

<br>

## 구성 요소
### 1.HeaderAuthenticationFilter
Gateway에서 인증 완료 후 서비스로 전달하는 헤더는 다음과 같습니다.<br>
헤더 값을 기반으로 SecurityContext에 Authentication 을 자동 주입합니다.

|Header| 설명     |
|-|--------|
|X-User_Id| 회원 ID  |
|X-User-Name| 회원 아이디 |
|X-User-Role| 권한 목록  |

<br>

### 2.AuthenticatedUser (UserDetails 구현체)
```java
public class AuthenticatedUser implements UserDetails {
    private final String id;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
}
```

<br>

### 3.Security Config
```java
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
```
