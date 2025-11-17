# Common Module
SmartLogis MSA 서비스에서 공통 기능을 제공하는 모듈입니다.
GitHub Packages에 배포되어 다른 서비스에서 의존성으로 추가하여 사용할 수 있습니다.

<br>

## 설치 방법
build.gradle
```groovy
repositories {
  mavenCentral()
  maven {
    name = "GitHubPackages"
    url = uri("https://maven.pkg.github.com/SmartLogisMsa/common-module")
    credentials {
      username = project.findProperty("gpr.user") ?: System.getenv("GITHUB_ACTOR")
      password = project.findProperty("gpr.token") ?: System.getenv("GITHUB_TOKEN")
    }
  }
}

dependencies {
    implementation 'com.smartlogis:common-autoconfigure:0.0.1'
}
```

<br>

### 주의사항
- common-module 버전을 꼭 확인하고 사용해주세요.
- 아래 의존성들은 common-module 내부에 포함되어 있습니다. 중복 선언 시 충돌 가능성이 있으므로 확인해주세요.
```groovy
  implementation 'org.springframework.boot:spring-boot-starter'
  implementation 'org.springframework.boot:spring-boot-starter-security'
  implementation 'org.springframework.boot:spring-boot-starter-web'
  implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

  compileOnly ('org.projectlombok:lombok:1.18.42')
  annotationProcessor 'org.projectlombok:lombok:1.18.42'
```

<br>

## 인증 정보 설정
application.yml
```yaml
gpr:
  username: ${GITHUB_ACTOR}
  token: ${GH_PAT}
```

<br>

github actions
```yaml
env:
  GITHUB_ACTION: ${{ github.actor }}
  GH_PAT: ${{ secrets.GH_PAT }}
```

<br>

## 제공 기능 목록

| 문서                       | 설명                                                   | 링크                                                 |
| ------------------------ | ---------------------------------------------------- |----------------------------------------------------|
| **API 응답 규칙**            | `ApiResponse` 구조와 메시지 전략 설명                          | [docs/ApiResponse.md](docs/ApiResponse.md)         |
| **예외 처리 가이드**            | `GlobalException`, `GlobalExceptionHandler` 사용 방법    | [docs/GlobalException.md](docs/GlobalException.md) |
| **인증/보안 구성**             | `HeaderAuthenticationFilter`, `AuthenticatedUser` 설명 | [docs/SpringSecurity.md](docs/SpringSecurity.md)   |
| **공통 엔티티 가이드**           | `AbstractEntity` 감사/삭제 처리 설명                         | [docs/AbstractEntity.md](docs/AbstractEntity.md)         |
