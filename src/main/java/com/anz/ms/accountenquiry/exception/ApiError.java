package com.anz.ms.accountenquiry.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ApiError {
    private String errorId;
    private String message;
    private HttpStatus status;
}
