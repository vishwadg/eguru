package com.example.commonmodule.exceptions;

/**
 * The type Not found exception.
 */
public class NotFoundException extends RuntimeException {
    /**
     * Instantiates a new Not found exception.
     */
    public NotFoundException() {
        super();
    }

    /**
     * Instantiates a new Not found exception.
     *
     * @param message the message
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Not found exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Not found exception.
     *
     * @param cause the cause
     */
    public NotFoundException(Throwable cause) {
        super(cause);
    }
}
