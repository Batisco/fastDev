package com.batisco.fastDev.model.exceptions;

public class IncorrectProductNameException extends RuntimeException {

    public IncorrectProductNameException() {
    }

    public IncorrectProductNameException(String message) {
        super(message);
    }

    public IncorrectProductNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectProductNameException(Throwable cause) {
        super(cause);
    }

}
