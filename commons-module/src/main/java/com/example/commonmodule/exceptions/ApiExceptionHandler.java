package com.example.commonmodule.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * The type Api exception handler.
 */
@ControllerAdvice
public class ApiExceptionHandler {
    /**
     * Handle api request exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(
                e.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }

    /**
     * Handle username or email used exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(value = {UsernameAlreadyUsedException.class, EmailAlreadyUsedException.class})
    public ResponseEntity<Object> handleUsernameOrEmailUsedException(RuntimeException e){
        String errorField;
        if(e instanceof UsernameAlreadyUsedException){
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

    /**
     * Bad requests exception response entity.
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(value = {BadRequestAlertException.class})
    public ResponseEntity<Object> badRequestsException(RuntimeException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }


    /**
     * The type Api exception.
     */
    public class ApiException{
        private  String message;
        private  HttpStatus httpStatus;
        private  ZonedDateTime zonedDateTime;
        private String errorField;

        /**
         * Instantiates a new Api exception.
         *
         * @param message the message
         */
        public ApiException(String message){
            this.message = message;
        }

        /**
         * Instantiates a new Api exception.
         *
         * @param message       the message
         * @param httpStatus    the http status
         * @param zonedDateTime the zoned date time
         */
        public ApiException(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
            this.message = message;
            this.httpStatus = httpStatus;
            this.zonedDateTime = zonedDateTime;
        }

        /**
         * Instantiates a new Api exception.
         *
         * @param message       the message
         * @param httpStatus    the http status
         * @param zonedDateTime the zoned date time
         * @param errorField    the error field
         */
        public ApiException(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime, String errorField) {
            this.message = message;
            this.httpStatus = httpStatus;
            this.zonedDateTime = zonedDateTime;
            this.errorField = errorField;
        }

        /**
         * Gets message.
         *
         * @return the message
         */
        public String getMessage() {
            return message;
        }

        /**
         * Gets http status.
         *
         * @return the http status
         */
        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        /**
         * Gets zoned date time.
         *
         * @return the zoned date time
         */
        public ZonedDateTime getZonedDateTime() {
            return zonedDateTime;
        }

        /**
         * Gets error field.
         *
         * @return the error field
         */
        public String getErrorField() {
            return errorField;
        }
    }
}
