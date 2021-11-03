package com.batisco.fastDev.model.exceptions;

public class NegativeValueException extends AbstractDomainException {

    public NegativeValueException() {
    }

    public NegativeValueException(String message) {
        super(message);
    }

    public NegativeValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeValueException(Throwable cause) {
        super(cause);
    }

}
