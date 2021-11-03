package com.batisco.fastDev.model.exceptions;

public class NotUniqueUserException extends AbstractDomainException {

    public NotUniqueUserException() {
    }

    public NotUniqueUserException(String message) {
        super(message);
    }

    public NotUniqueUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotUniqueUserException(Throwable cause) {
        super(cause);
    }

}
