package com.batisco.fastDev.model.exceptions;

public class UserAlreadyHaveProductException extends RuntimeException {

    public UserAlreadyHaveProductException() {
    }

    public UserAlreadyHaveProductException(String message) {
        super(message);
    }

    public UserAlreadyHaveProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyHaveProductException(Throwable cause) {
        super(cause);
    }

}
