package com.move.move.exception;

public class DateCalculationException extends RuntimeException {

    public DateCalculationException(String message) {
        super(message);
    }

    public DateCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}