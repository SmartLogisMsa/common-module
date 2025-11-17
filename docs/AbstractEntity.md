# AbstractEntity (JPA Auditing + Soft Delete)
`AbstractEntity`는 공통 모듈에서 제공하는 감사 및 소프트 삭제 기능을 포함한 공통 엔티티 추상 클래스입니다.

<br>

## 사용 방법
### 엔티티 상속
```java
@Entity
public class Order extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNo;
}
```

<br>

### Auditing 활성화
```java
@EnableJpaAuditing
@SpringBootApplication
public class OrderServiceApplication {
}
```

## 제공 기능
### 1) 생성/수정 감사 정보 자동 관리
Spring Data JPA Auditing을 기반으로 다음 필드가 자동으로 채워집니다.

|필드|설명|
|-|-|
|createdAt|생성 일시|
|createdBy|생성자|
|updatedAt|수정 일시|
|updatedBy|수정자|

**동작 방식**
- `@CreatedDate`, `@LastModifiedDate`로 시간 자동 관리
- `@CreatedBy`, `@LastModifiedBy`로 사용자 자동 관리
- 사용자 정보는 AuditorAware 구현체에서 제공
- Spring Security Authentication 기반

<br>

### 2) Soft Delete 기능 제공
엔티티 삭제 시 실제 DB 삭제가 아닌 소프트 삭제 방식을 제공합니다.

| 필드        | 설명    |
|-----------|-------|
| deletedAt | 삭제 일시 |
| deletedBy | 삭제자   |

삭제는 `delete()` 메서드를 호출하여 수행합니다.

```java
public void delete() {
    this.deletedAt = LocalDateTime.now();
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    deletedBy(authentication == null ? "SYSTEM" : authentication.getName());
}
```

<br>

### 3) 생성자, 수정자 수동 등록
```java
public class User extends AbstractEntity {
    ...
    
    public static User create(UserCreateRequest request) {
        User user = new User();
        
        user.createBy(request.username());
    }
}
```