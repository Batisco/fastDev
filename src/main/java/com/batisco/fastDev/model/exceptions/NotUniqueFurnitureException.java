package com.batisco.fastDev.model.exceptions;

public class NotUniqueFurnitureException extends AbstractDomainException {

    public NotUniqueFurnitureException() {

    }

    public NotUniqueFurnitureException(String message) {
        super(message);
    }

    public NotUniqueFurnitureException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotUniqueFurnitureException(Throwable cause) {
        super(cause);
    }

}
