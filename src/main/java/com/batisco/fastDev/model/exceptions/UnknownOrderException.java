package com.batisco.fastDev.model.exceptions;

public class UnknownOrderException extends AbstractDomainException {

    public UnknownOrderException() {
    }

    public UnknownOrderException(String message) {
        super(message);
    }

    public UnknownOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownOrderException(Throwable cause) {
        super(cause);
    }

}
