package com.example.commonmodule.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                e.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {UsernameAlreadyUsedException.class, EmailAlreadyUsedException.class})
    public ResponseEntity<Object> handleUsernameOrEmailUsedException(RuntimeException e) {
        String errorField;
        if (e instanceof UsernameAlreadyUsedException) {
            errorField = "username";
        } else {
            errorField = "email";
        }

        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ApiException apiException = new ApiException(
                errorField + " already used"
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(value = {BadRequestAlertException.class})
    public ResponseEntity<Object> badRequestsException(RuntimeException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }


    public class ApiException {
        private String message;
        private HttpStatus httpStatus;
        private ZonedDateTime zonedDateTime;
        private String errorField;

        public ApiException(String message) {
            this.message = message;
        }

        public ApiException(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
            this.message = message;
            this.httpStatus = httpStatus;
            this.zonedDateTime = zonedDateTime;
        }

        public ApiException(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime, String errorField) {
            this.message = message;
            this.httpStatus = httpStatus;
            this.zonedDateTime = zonedDateTime;
            this.errorField = errorField;
        }

        public String getMessage() {
            return message;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public ZonedDateTime getZonedDateTime() {
            return zonedDateTime;
        }

        public String getErrorField() {
            return errorField;
        }
    }
}
