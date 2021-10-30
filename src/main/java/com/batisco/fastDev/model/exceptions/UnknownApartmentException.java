package com.batisco.fastDev.model.exceptions;

public class UnknownApartmentException extends RuntimeException {

    public UnknownApartmentException() {
    }

    public UnknownApartmentException(String message) {
        super(message);
    }

    public UnknownApartmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownApartmentException(Throwable cause) {
        super(cause);
    }

}
