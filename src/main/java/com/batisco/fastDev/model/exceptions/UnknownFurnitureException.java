package com.batisco.fastDev.model.exceptions;

public class UnknownFurnitureException extends AbstractDomainException {

    public UnknownFurnitureException() {

    }

    public UnknownFurnitureException(String message) {
        super(message);
    }

    public UnknownFurnitureException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownFurnitureException(Throwable cause) {
        super(cause);
    }

}
