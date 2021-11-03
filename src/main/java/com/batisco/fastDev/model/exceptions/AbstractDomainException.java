package com.batisco.fastDev.model.exceptions;

public abstract class AbstractDomainException extends RuntimeException {

    public AbstractDomainException() {
    }

    public AbstractDomainException(String message) {
        super(message);
    }

    public AbstractDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractDomainException(Throwable cause) {
        super(cause);
    }

}
