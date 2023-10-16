package com.example.commonmodule.exceptions;

/**
 * The type Username already used exception.
 */
public class UsernameAlreadyUsedException extends RuntimeException {
    /**
     * Instantiates a new Username already used exception.
     */
    public UsernameAlreadyUsedException() {
        super("Username Already Used");
    }
}
