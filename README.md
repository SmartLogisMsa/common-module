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

dependencies {
    implementation 'com.github.smartlogismsa:common-module:0.0.2'
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
api 'org.springframework.boot:spring-boot-starter-data-jpa'
api 'org.springframework.boot:spring-boot-starter-security'
```

<br>

## 제공 기능 목록

| 문서             | 설명                                                   | 링크                                                 |
|----------------|------------------------------------------------------|----------------------------------------------------|
| **API 응답 규칙**  | `ApiResponse` 구조와 메시지 전략 설명                          | [docs/ApiResponse.md](docs/ApiResponse.md)         |
| **예외 처리 가이드**  | `GlobalException`, `GlobalExceptionHandler` 사용 방법    | [docs/GlobalException.md](docs/GlobalException.md) |
| **인증/보안 구성**   | `HeaderAuthenticationFilter`, `AuthenticatedUser` 설명 | [docs/SpringSecurity.md](docs/SpringSecurity.md)   |
| **공통 엔티티 가이드** | `AbstractEntity` 감사/삭제 처리 설명                         | [docs/AbstractEntity.md](docs/AbstractEntity.md)   |
