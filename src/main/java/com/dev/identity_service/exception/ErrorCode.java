package com.dev.identity_service.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "System error!"),
    INVALID_KEY(1001, "Invalid key (coding, refactor...)"),
    USER_EXISTED(2001, "User existed!"),
    USER_NOT_FOUND(2002, "User not found!"),
    USER_NOT_EXISTED(2003, "User not existed!"),
    USERNAME_INVALID(3003, "Username must be at least 3 characters!"),
    PASSWORD_INVALID(3004, "Password must be at least 6 characters!")
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
