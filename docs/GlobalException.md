# Global Exception Handling
## 사용 방법
1. MessageCode Enum 정의
```java
public enum OrderCode implements MessageCode {
    INVALID_AMOUNT("ORDER.INVALID_AMOUNT", HttpStatus.BAD_REQUEST);
}
```

<br>

2. CustomException 작성
```java
public class InvalidAmountException extends AbstractException {
    public InvalidAmountException(int amount) {
        super(OrderCode.INVALID_AMOUNT, amount);
    }
}
```

<br>

3. 서비스 코드에서 throw
```java
if (amount <= 0) {
    throw new InvalidAmountException(amount);
}
```

4. message.properties 작성
```properties
ORDER.INVALID_AMOUNT=잘못된 가격입니다.
```

## 구성 요소
1. `AbstractException`
서비스에서 발생시키는 모든 도메인/비즈니스 예외의 부모 클래스입니다.
```java
public abstract class AbstractException extends RuntimeException {
    private final MessageCode messageCode;
    private final Object[] messageArguments;
}
```

<br>

2. `MessageCode`
```java
public interface MessageCode {
    String getCode();
    HttpStatus getStatus();
}
```

<br>

예시:
```java
public enum UserMessageCode implements MessageCode {
    USER_NOT_FOUND("USER.NOT_FOUND", HttpStatus.NOT_FOUND);
}
```

<br>

3. 'GlobalExceptionHandler'
서비스 전역에서 발생한 예외를 처리하는 공통 Handler 입니다.
```java
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<ApiResponse<Void>> globalException(AbstractException e) {}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> exception(Exception e) {}
}
```

<br>

처리 방식:
| 예외 타입 | 처리 내용|
|-|-|
|`AbstractException`| MessageCode 기반 응답 생성, HttpStatus지정, messageArguments 포함 가능 |
| 그 외 모든 `Exception` | `{서비스명}.UNKNWON` 코드로 처리, 500 응답 반환 |

<br>

4. `MessageResolver`
각 서비스는 `message.properties` 파일만 구성하면 메세지가 자동으로 매핑됩니다.
```properties
USER.NOT_FOUND=회원을 찾을 수 없습니다.
```

<br>

## Exception 응답 구조
공통 모듈에서 제공하는 `ApiResponse.failure()` 구조를 따릅니다.
```json
{
  "success": false,
  "code": "ORDER.INVALID",
  "message": "주문 금액이 올바르지 않습니다: 10000",
  "args": [10000]
}
```