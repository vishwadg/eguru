package com.example.commonmodule.exceptions;

public class BadRequestAlertException extends RuntimeException{
    public BadRequestAlertException(String message) {
        super(message);
    }
}
