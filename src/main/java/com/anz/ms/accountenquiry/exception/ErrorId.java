package com.anz.ms.accountenquiry.exception;

import lombok.Getter;

@Getter
public enum ErrorId {
    DATA_NOT_FOUND ("DATA_NOT_FOUND"),
    UNKNOWN ("UNKNOWN");

    private String errorId;

    ErrorId(String errorId) {
        this.errorId = errorId;
    }
}
