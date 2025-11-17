# ApiResponse
모든 API 응답을 일관된 형식으로 제공하기 위해 만들어진 공통 응답 객체입니다.

<br>

```java
@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {
    private final boolean success;
    private final String messageCode;
    private final String message;
    private final T data;
}
```

|필드|설명|
|-|-|
|success|요청 성공 여부|
|messageCode|메세지 키|
|message|messageCode를 기반으로 MessageResolver에서 조회한 실제 사용자 메세지|
|data| API 응답 데이터|

<br>

## 사용 방법
```java
@GetMapping()
public ResponseEntity<ApiResponse<UserResponse>> getUser() {
    return ok(success());
}
```