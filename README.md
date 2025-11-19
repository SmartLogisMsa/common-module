# Common Module

SmartLogis MSA 서비스에서 공통 기능을 제공하는 모듈입니다.
GitHub Packages에 배포되어 다른 서비스에서 의존성으로 추가하여 사용할 수 있습니다.

<br>

## 설치 방법

build.gradle

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

ext {
    set('springCloudVersion', "2025.0.0")
}

dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
    }
}

dependencies {
    implementation 'com.github.smartlogismsa:common-module:0.0.5'
}
```

<br>

### 주의사항

- common-module 버전을 꼭 확인하고 사용해주세요.
- [JitPack](https://jitpack.io/#smartlogismsa/common-module) 에서 최신 버전 확인 가능합니다.
- 아래 의존성들은 common-module 내부에 포함되어 있습니다. 중복 선언 시 충돌 가능성이 있으므로 확인해주세요.

```groovy
api 'org.springframework.boot:spring-boot-starter'
api 'org.springframework.boot:spring-boot-starter-web'
api 'org.springframework.boot:spring-boot-starter-validation'
api 'org.springframework.boot:spring-boot-starter-data-jpa'
api 'org.springframework.boot:spring-boot-starter-security'
api 'org.springframework.boot:spring-boot-starter-aop'
api 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13'

// eureka, config client
api 'org.springframework.cloud:spring-cloud-starter-config'
api 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'

// openfeign, loadbalancer
api 'org.springframework.cloud:spring-cloud-starter-openfeign'
api 'org.springframework.cloud:spring-cloud-starter-loadbalancer'

// resilience4j
api 'io.github.resilience4j:resilience4j-spring-boot3:2.3.0'
api 'io.github.resilience4j:resilience4j-circuitbreaker:2.3.0'
api 'io.github.resilience4j:resilience4j-retry:2.3.0'
api 'io.github.resilience4j:resilience4j-bulkhead:2.3.0'
api 'io.github.resilience4j:resilience4j-ratelimiter:2.3.0'

// monitoring
api 'io.micrometer:micrometer-core:1.16.0'
api 'io.micrometer:micrometer-registry-prometheus:1.16.0'
api 'io.micrometer:micrometer-tracing-bridge-brave:1.6.0'
api 'io.zipkin.reporter2:zipkin-reporter-brave:3.5.1'
api 'com.github.loki4j:loki-logback-appender:2.0.0'
api 'org.springframework.boot:spring-boot-starter-actuator'

// rabbitmq
api 'org.springframework.boot:spring-boot-starter-amqp'
api 'org.springframework.cloud:spring-cloud-starter-stream-rabbit'
api 'org.springframework.retry:spring-retry'
api 'org.springframework:spring-aspects'
```

<br>

## 제공 기능 목록

| 문서             | 설명                                                   | 링크                                                 |
|----------------|------------------------------------------------------|----------------------------------------------------|
| **API 응답 규칙**  | `ApiResponse` 구조와 메시지 전략 설명                          | [docs/ApiResponse.md](docs/ApiResponse.md)         |
| **예외 처리 가이드**  | `GlobalException`, `GlobalExceptionHandler` 사용 방법    | [docs/GlobalException.md](docs/GlobalException.md) |
| **인증/보안 구성**   | `HeaderAuthenticationFilter`, `AuthenticatedUser` 설명 | [docs/SpringSecurity.md](docs/SpringSecurity.md)   |
| **공통 엔티티 가이드** | `AbstractEntity` 감사/삭제 처리 설명                         | [docs/AbstractEntity.md](docs/AbstractEntity.md)   |
| **공통 Page DTO**| `PageRequest`, `PageResponse` ||
