package com.anz.ms.accountenquiry.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class AccountEnquiryExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApiError> handleDataNotFoundException(final DataNotFoundException exception) {
        logException(exception);

        return new ResponseEntity<>(
                ApiError
                        .builder()
                        .errorId(ErrorId.DATA_NOT_FOUND.getErrorId())
                        .message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND)
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserParameterInvalidException.class)
    public ResponseEntity<ApiError> handleUserParameterInvalidException(final UserParameterInvalidException exception) {
        logException(exception);

        return new ResponseEntity<>(
                ApiError
                        .builder()
                        .errorId(ErrorId.USER_PARAM_INVALID.getErrorId())
                        .message(exception.getMessage())
                        .status(HttpStatus.BAD_REQUEST)
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(final Exception exception) {
        logException(exception);

        return new ResponseEntity<>(
                ApiError
                        .builder()
                        .errorId(ErrorId.UNKNOWN.getErrorId())
                        .message(exception.getMessage())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logException(Throwable exception) {
        log.error("message=\"Exception in ms-accounts-enquiry\", exception=", exception);
    }
}
