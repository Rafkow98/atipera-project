package com.task.atipera.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}