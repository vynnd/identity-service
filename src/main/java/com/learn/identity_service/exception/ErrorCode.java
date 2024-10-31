package com.learn.identity_service.exception;

public enum ErrorCode {

    INVALID_KEY(1001,"Invalid message key"),
    USER_EXISTED(1002, "User existed"),
    USERNAME_INVALID(1003,"User name must be least 3 characters"),
    PASSWORD_INVALID(1004,"Password must be least 8 characters"),
    UNCATEGORIZED_EXISTED(9999, "Uncategorized exception"),
    USER_NOT_EXISTED(1005, "User not existed"),
    UNAUTHENTICATED(1006, "Unauthenticated"),
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
