package com.example.commonmodule.exceptions;

/**
 * The type Email already used exception.
 */
public class EmailAlreadyUsedException extends RuntimeException {
    /**
     * Instantiates a new Email already used exception.
     */
    public EmailAlreadyUsedException() {
        super("Email Already Used");
    }
}
