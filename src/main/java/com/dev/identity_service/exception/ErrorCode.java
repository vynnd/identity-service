package com.dev.identity_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "System error!", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid key (coding, refactor...)", HttpStatus.BAD_REQUEST),
    INVALID_DOB(1002, "Invalid day of birth (> 18 years!)", HttpStatus.BAD_REQUEST),
    USER_EXISTED(2001, "User existed!", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(2002, "User not found!", HttpStatus.NOT_FOUND),
    USER_NOT_EXISTED(2003, "User not existed!", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(3003, "Username must be at least 3 characters!", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(3004, "Password must be at least 6 characters!", HttpStatus.BAD_REQUEST),
    UN_AUTHENTICATED(4001, "Unauthenticated!", HttpStatus.UNAUTHORIZED),
    ACCESS_DENINED(4002, "You do not have permission!", HttpStatus.FORBIDDEN),
    PARSE_ERROR(5001, "Parse Exception!", HttpStatus.BAD_REQUEST),
    DATABASE_ERROR(6001, "Database Exception!", HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXISTED(7001, "Role not existed!", HttpStatus.BAD_REQUEST)
    ;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatus;
    }

    private int code;
    private String message;
    private HttpStatus httpStatusCode;

}
