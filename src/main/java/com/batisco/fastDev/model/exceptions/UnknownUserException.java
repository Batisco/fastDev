package com.batisco.fastDev.model.exceptions;

public class UnknownUserException extends AbstractDomainException {

    public UnknownUserException() {
    }

    public UnknownUserException(String message) {
        super(message);
    }

    public UnknownUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownUserException(Throwable cause) {
        super(cause);
    }

}
