package com.anz.ms.accountenquiry.exception;

import lombok.Getter;

@Getter
public enum ErrorId {
    DATA_NOT_FOUND ("DATA_NOT_FOUND"),
    UNKNOWN ("UNKNOWN"),
    USER_PARAM_INVALID("USER_PARAM_INVALID");

    private String errorId;

    ErrorId(String errorId) {
        this.errorId = errorId;
    }
}
